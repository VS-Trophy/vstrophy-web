package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.seasons.Season;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.VertexEntity;
import com.arangodb.util.MapBuilder;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Map;
import javax.annotation.PreDestroy;
import org.slf4j.LoggerFactory;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArangoPersistenceHandler implements PersistenceHandler {

  //DBs
  private static final String VSTROPHY_DATABASE = "vs-trophy";
  //Graphs
  private static final String SEASON_GRAPH = "SeasonGraph";
  //Vertex Collections
  private static final String WEEKS_COLLECTION = "Weeks";
  private static final String MATCHES_COLLECTION = "Matches";
  private static final String SEASONS_COLLLECTION = "Seasons";

  //Edge Collections
  private static final String WEEKS_IN_SEASON_COLLECTION = "WeeksInSeason";
  private static final String MATCHES_IN_WEEK_COLLECTION = "MatchesInWeek";
  private static final String TEAM_PLAYED_IN_COLLECTION = "TeamPlayedIn";

  private static final org.slf4j.Logger LOGGER
      = LoggerFactory.getLogger(ArangoPersistenceHandler.class);

  private ArangoDatabase database;

  @PostConstruct
  protected void init() {
    ArangoDB arangoDB = new ArangoDB.Builder().build();
    database = arangoDB.db(VSTROPHY_DATABASE);
  }

  @PreDestroy
  protected void tearDown() {
    database.arango().shutdown();
  }

  @Override
  public Map<Integer, Team> getTeamMap() {
    return null;
  }

  @Override
  public Week getOrCreateWeek(int seasonNumber, int weekNumber) throws GolemPersistenceException {
    Week week = getWeek(seasonNumber, weekNumber, Week.class);
    if (week == null) {
      week = createWeekAndSeason(seasonNumber, weekNumber);
    }
    return week;
  }

  private Week createWeekAndSeason(int seasonNumber, int weekNumber) throws GolemPersistenceException {
    //create season if it does not exist
    BaseDocument seasonDocument = getSeason(seasonNumber);
    String seasonId = null;
    if (seasonDocument == null) {
      Season season = new Season(seasonNumber);
      DocumentCreateEntity<Season> seasonCreateEntity
          = database.collection(SEASONS_COLLLECTION).insertDocument(season);
      seasonId = seasonCreateEntity.getId();
    } else {
      seasonId = seasonDocument.getId();
    }
    //create week
    Week week = new Week(weekNumber);
    DocumentCreateEntity<Week> weekCreateEntity
        = database.collection(WEEKS_COLLECTION).insertDocument(week);

    BaseEdgeDocument edge
        = new BaseEdgeDocument(seasonId, weekCreateEntity.getId());

    database
        .graph(SEASON_GRAPH)
        .edgeCollection(WEEKS_IN_SEASON_COLLECTION)
        .insertEdge(edge);

    return week;
  }

  private <T> T getWeek(int seasonNumber, int weekNumber, Class<T> clazz) throws GolemPersistenceException {
    String query = Queries.GET_WEEK;
    Map<String, Object> bindVars = new MapBuilder()
        .put("week", weekNumber)
        .put("season", seasonNumber)
        .get();
    ArangoCursor<T> cursor
        = database.query(query, bindVars, null, clazz);
    List<T> weeks = cursor.asListRemaining();
    if (weeks.size() > 1) {
      throw new GolemPersistenceException(
          "Got " + weeks.size()
          + " weeks for season "
          + seasonNumber + " and week " + weekNumber);
    }
    if (weeks.isEmpty()) {
      return null;
    } else {
      return weeks.get(0);
    }
  }

  protected BaseDocument getTeam(String nflId) throws GolemPersistenceException {
    String query = Queries.GET_TEAM;
    Map<String, Object> bindVars = new MapBuilder()
        .put("nflId", nflId).get();

    ArangoCursor<BaseDocument> cursor
        = database.query(query, bindVars, null, BaseDocument.class);
    List<BaseDocument> teams = cursor.asListRemaining();
    if (teams.size() > 1) {
      throw new GolemPersistenceException(
          "Got " + teams.size()
          + " steams for "
          + nflId);
    }
    if (teams.isEmpty()) {
      return null;
    } else {
      return teams.get(0);
    }
  }

  private BaseDocument getSeason(int seasonNumber) throws GolemPersistenceException {
    String query = Queries.GET_SEASON;
    Map<String, Object> bindVars = new MapBuilder()
        .put("season", seasonNumber)
        .get();
    ArangoCursor<BaseDocument> cursor
        = database.query(query, bindVars, null, BaseDocument.class);
    List<BaseDocument> seasons = cursor.asListRemaining();
    if (seasons.size() > 1) {
      throw new GolemPersistenceException(
          "Got " + seasons.size()
          + " seasons for season "
          + seasonNumber);
    }
    if (seasons.isEmpty()) {
      return null;
    } else {
      return seasons.get(0);
    }
  }

  protected BaseDocument getMatch(int seasonNumber, int weekNumber, String firstTeamNflId, String secondTeamNflId) throws GolemPersistenceException {
    String query = Queries.GET_SPECIFIC_MATCH;
    Map<String, Object> bindVars = new MapBuilder()
        .put("season", seasonNumber)
        .put("week", weekNumber)
        .put("firstNflId", firstTeamNflId)
        .put("secondNflId", secondTeamNflId)
        .get();
    ArangoCursor<BaseDocument> cursor
        = database.query(query, bindVars, null, BaseDocument.class);
    List<BaseDocument> matches = cursor.asListRemaining();
    if (matches.size() > 1) {
      throw new GolemPersistenceException(
          "Got " + matches.size()
          + " matches for season "
          + seasonNumber
          + " week "
          + weekNumber
          + " and teams "
          + firstTeamNflId
          + " and "
          + secondTeamNflId);
    }
    if (matches.isEmpty()) {
      return null;
    } else {
      return matches.get(0);
    }
  }

  @Override
  public void updateOrCreateMatch(int seasonNumber, int weekNumber, Match match) throws GolemPersistenceException {
    BaseDocument existingMatch = 
        getMatch(seasonNumber, weekNumber, match.getFirstTeamId(), match.getSecondTeamId());
    if(existingMatch != null){
      //TODO update edges
      LOGGER.info("Match exists already, skipping...");
    } else {
      LOGGER.info("Inserting match into graph...");
    BaseDocument week = getWeek(seasonNumber, weekNumber, BaseDocument.class);
    BaseDocument firstTeam = getTeam(match.getFirstTeamId());
    BaseDocument secondTeam = getTeam(match.getSecondTeamId());
    //Create the match vertex
    VertexEntity matchEntity
        = database
            .graph(SEASON_GRAPH)
            .vertexCollection(MATCHES_COLLECTION)
            .insertVertex(new BaseDocument());

    BaseEdgeDocument matchInWeek
        = new BaseEdgeDocument(week.getId(), matchEntity.getId());
    //Connect match and week
    database
        .graph(SEASON_GRAPH)
        .edgeCollection(MATCHES_IN_WEEK_COLLECTION)
        .insertEdge(matchInWeek);

    TeamPerformanceEdge firstTeamEdge = new TeamPerformanceEdge(firstTeam.getId(), matchEntity.getId());
    firstTeamEdge.setPoints(match.getFirstTeamPoints());

    TeamPerformanceEdge secondTeamEdge = new TeamPerformanceEdge(secondTeam.getId(), matchEntity.getId());
    secondTeamEdge.setPoints(match.getSecondTeamPoints());
    //Connect match and teams
    database
        .graph(SEASON_GRAPH)
        .edgeCollection(TEAM_PLAYED_IN_COLLECTION)
        .insertEdge(firstTeamEdge);

    database
        .graph(SEASON_GRAPH)
        .edgeCollection(TEAM_PLAYED_IN_COLLECTION)
        .insertEdge(secondTeamEdge);
    LOGGER.info("...done");
    }
  }
}

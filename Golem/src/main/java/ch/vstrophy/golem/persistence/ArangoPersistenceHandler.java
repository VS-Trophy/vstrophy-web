package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.seasons.Season;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.golem.VSTrophyGolem;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.DocumentEntity;
import com.arangodb.util.MapBuilder;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Map;
import org.slf4j.LoggerFactory;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArangoPersistenceHandler implements PersistenceHandler {

  private static final String VSTROPHY_DATABASE = "vs-trophy";
    private static final String SEASON_GRAPH = "SeasonGraph";
  private static final String WEEKS_COLLECTION = "Weeks";
  private static final String SEASONS_COLLLECTION = "Seasons";
  private static final String WEEKS_IN_SEASON_COLLECTION = "WeeksInSeason";

  private static final org.slf4j.Logger LOGGER
      = LoggerFactory.getLogger(ArangoPersistenceHandler.class);

  private ArangoDatabase database;

  @PostConstruct
  protected void init() {
    ArangoDB arangoDB = new ArangoDB.Builder().build();
    database = arangoDB.db(VSTROPHY_DATABASE);
  }

  @Override
  public Map<Integer, Team> getTeamMap() {
    return null;
  }

  @Override
  public void saveWeek(Week week) throws GolemPersistenceException{

  }

  @Override
  public void saveMatch(Match match) throws GolemPersistenceException{

  }

  @Override
  public Week getOrCreateWeek(int seasonNumber, int weekNumber) throws GolemPersistenceException{
    Week week = getWeek(seasonNumber, weekNumber);
    if(week == null){
      week = createWeekAndSeason(seasonNumber, weekNumber);
    }
    return week;
  }

  @Override
  public Match getOrCreateMatch(Week week, Team firstTeam, Team secondTeam) throws GolemPersistenceException {
    return null;
  }
  
  private Week createWeekAndSeason(int seasonNumber, int weekNumber) throws GolemPersistenceException{
    //create season if it does not exist
    BaseDocument seasonDocument = getSeason(seasonNumber);
    String seasonId = null;
    if(seasonDocument == null){
      Season season = new Season(seasonNumber);
      DocumentCreateEntity<Season> seasonCreateEntity = 
          database.collection(SEASONS_COLLLECTION).insertDocument(season);
      seasonId = seasonCreateEntity.getId();
    } else{
      seasonId = seasonDocument.getId();
    }
    //create week
    Week week = new Week(weekNumber);
    DocumentCreateEntity<Week> weekCreateEntity = 
        database.collection(WEEKS_COLLECTION).insertDocument(week);
    
    BaseEdgeDocument edge = 
        new BaseEdgeDocument(seasonId, weekCreateEntity.getId());
    
    database
        .graph(SEASON_GRAPH)
        .edgeCollection(WEEKS_IN_SEASON_COLLECTION)
        .insertEdge(edge);
    
    return week;
  }

         
      
  private Week getWeek(int seasonNumber, int weekNumber) throws GolemPersistenceException {
    String query = Queries.GET_WEEK;
    Map<String, Object> bindVars = new MapBuilder()
        .put("week", weekNumber)
        .put("season", seasonNumber)
        .get();
    ArangoCursor<Week> cursor
        = database.query(query, bindVars, null, Week.class);
    List<Week> weeks = cursor.asListRemaining();
    if (weeks.size() > 1) {
      throw new GolemPersistenceException(
          "Got " + weeks.size()
          + " weeks for season "
          + seasonNumber + " and week " + weekNumber);
    }
    if(weeks.isEmpty()){
      return null;
    } else {
      return weeks.get(0);
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
    if(seasons.isEmpty()){
      return null;
    } else {
      return seasons.get(0);
    }
  }
}

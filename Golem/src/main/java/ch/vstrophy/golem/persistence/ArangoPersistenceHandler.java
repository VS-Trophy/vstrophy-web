package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import com.arangodb.ArangoDB;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Map;


@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArangoPersistenceHandler implements PersistenceHandler {

    private ArangoDB arangoDB;

    @PostConstruct
    private void init(){
        arangoDB = new ArangoDB.Builder().build();
    }

    @Override
    public Map<Integer, Team> getTeamMap() {
        return null;
    }

    @Override
    public void saveWeek(Week week) {

    }

    @Override
    public void saveMatch(Match match) {

    }

    @Override
    public Week getOrCreateWeek(int season, int weekNumber) {
        return null;
    }

    @Override
    public Match getOrCreateMatch(Week week, Team firstTeam, Team secondTeam) {
        return null;
    }
}

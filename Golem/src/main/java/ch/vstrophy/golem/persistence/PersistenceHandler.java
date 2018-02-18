package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;

import java.util.Map;

public interface PersistenceHandler {
    Map<Integer, Team> getTeamMap();

    void saveWeek(Week week);

    void saveMatch(Match match);

    Week getOrCreateWeek(int season, int weekNumber);

    Match getOrCreateMatch(Week week, Team firstTeam, Team secondTeam);
}

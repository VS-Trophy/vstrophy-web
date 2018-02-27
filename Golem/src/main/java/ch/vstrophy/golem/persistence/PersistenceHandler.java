package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;

import java.util.Map;

public interface PersistenceHandler {
    Map<Integer, Team> getTeamMap();

    void saveMatch(int seasonNumber, int weekNumber, Match match) throws GolemPersistenceException;

    Week getOrCreateWeek(int season, int weekNumber) throws GolemPersistenceException;

}

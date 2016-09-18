/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.match.MatchEntityManager;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamEntityManager;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.entities.weeks.WeekEntityManager;
import ch.vstrophy.golem.GolemService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
@LocalBean
public class PersistenceHandler {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GolemService.class);
    @Inject
    private TeamEntityManager teamEntityManager;
    
    @Inject
    private WeekEntityManager weekEntityManager;
    
    @Inject
    private MatchEntityManager matchEntityManager;

    /**
     * Returns a map with all teams, mapped by their NFL ID
     *
     * @return
     */
    public Map<Integer, Team> getTeamMap() {
        Map<Integer, Team> teamMap = new HashMap<>();
        List<Team> teamList = teamEntityManager.getAllTeams();
        for (Team team : teamList) {
            teamMap.put(team.getNflId(), team);
        }
        return teamMap;
    }
    
    public void saveWeek(final Week week) {
        weekEntityManager.saveWeek(week);
    }
    
    public void saveMatch(final Match match) {
        matchEntityManager.saveMatch(match);
    }
    
    public Week getOrCreateWeek(int season, int weekNumber) {
        
        Week week = weekEntityManager.getWeek(season, weekNumber);
        if (week == null) {
            week = new Week(season, weekNumber);
            weekEntityManager.saveWeek(week);
        }
        return week;
        
    }

    /**
     * The order of the teams does not matter
     *
     * @param week
     * @param firstTeam
     * @param secondTeam
     * @return
     */
    public Match getOrCreateMatch(Week week, Team firstTeam, Team secondTeam) {
        
        Match match = null;
        try {
            matchEntityManager.getMatch(week, firstTeam, secondTeam);
        } catch (Exception ex) {
            LOGGER.warn("Error while getting or creating match",ex);
        }
        if (match == null) {
            match = new Match();
            match.setFirstTeam(firstTeam);
            match.setSecondTeam(secondTeam);
            week.addMatch(match);
        }
        return match;
    }
}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

import ch.vstrophy.common.WeekInfoProvider;
import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.match.MatchEntityManager;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.exception.VSTrophyException;
import java.util.ArrayList;
import java.util.List;
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
public class TeamStatisticProvider {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeamStatisticProvider.class);
    @Inject
    private WeekInfoProvider weekInfo;

    @Inject
    private MatchEntityManager matchEntityManager;

    public TeamRecord getTotalRecord(Team team) throws VSTrophyException {
        if (team == null) {
            throw new NullPointerException("Team must not be null!");
        }
        List<Match> matches = matchEntityManager.getAllMatches(team);
        matches = removeCurrentAndFutureMatches(matches);
        return getRecordForTeam(team, matches);
    }

    public TeamRecord getCurrentRecord(Team team) throws VSTrophyException {
        if (team == null) {
            throw new NullPointerException("Team must not be null!");
        }
        LOGGER.info("Getting current record for " + team);
        List<Match> matches = matchEntityManager.getMatchesForSeason(team, weekInfo.getCurrentSeasonNumber());
        for (Match match : matches) {
            LOGGER.info(match.getFirstTeam() + " vs " + match.getSecondTeam() + " in Week " + match.getWeek().getNumber() + " " + match.getWeek().getSeason());
        }
        //We remove the current week as the results are not yet final
        matches = removeCurrentAndFutureMatches(matches);
        return getRecordForTeam(team, matches);
    }

    private TeamRecord getRecordForTeam(Team team, List<Match> matches) throws VSTrophyException {
        TeamRecord record = new TeamRecord();
        for (Match match : matches) {

            boolean isFirstTeam = team.equals(match.getFirstTeam());
            boolean isSecondTeam = team.equals(match.getSecondTeam());

            if (!(isFirstTeam || isSecondTeam)) {
                //this should not happen but we check it anyway
                throw new VSTrophyException("Team " + team + " is not represented in match!");
            }
            if (isFirstTeam && isSecondTeam) {
                //this should not happen but we check it anyway
                throw new VSTrophyException("Match " + match + " has the same team twice!");
            }
            if (match.getFirstTeamPoints() == match.getSecondTeamPoints()) {
                //if both teams have the same number of points its a draw
                record.addDraw();
                continue;
            }
            boolean firstTeamWins = match.getFirstTeamPoints() > match.getSecondTeamPoints();
            if ((isFirstTeam && firstTeamWins)
                    || (isSecondTeam && !firstTeamWins)) {
                //if we are first team and first team is higher or if we are second team and second team is higher we win
                record.addWin();
            } else {
                //ohterwise we lose
                record.addLoss();
            }
        }
        return record;
    }

    private List<Match> removeCurrentAndFutureMatches(List<Match> matches) {
        List<Match> modifiedMatches = new ArrayList<>(matches);
        for (Match match : matches) {
            if (match.getWeek().getSeason() < weekInfo.getCurrentSeasonNumber()) {
                continue;
            }
            if (match.getWeek().getSeason() < weekInfo.getCurrentSeasonNumber()) {
                modifiedMatches.remove(match);
                continue;
            }
            if (match.getWeek().getNumber() >= weekInfo.getCurrentWeekNumber()) {
                modifiedMatches.remove(match);
                continue;
            }
        }
        return modifiedMatches;
    }

}

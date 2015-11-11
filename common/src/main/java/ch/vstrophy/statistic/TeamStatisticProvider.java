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
import java.util.Iterator;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateful
@LocalBean
public class TeamStatisticProvider {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeamStatisticProvider.class);
    @Inject
    private WeekInfoProvider weekInfo;

    @Inject
    private MatchEntityManager matchEntityManager;

    private Team team;

    private List<Match> allMatches;
    private List<Match> currentMatches;

    public void setTeam(Team team) {
        this.team = team;
        allMatches = matchEntityManager.getAllMatches(team);
        allMatches = removeCurrentAndFutureMatches(allMatches);
        currentMatches = removeMatchesFromOtherSeasonsAndFuture(allMatches);
    }

    public TeamRecord getTotalRecord() throws VSTrophyException {
        if (team == null) {
            throw new NullPointerException("Team must not be null!");
        }
        return getRecordForTeam(allMatches);
    }

    public TeamRecord getCurrentRecord() throws VSTrophyException {
        if (team == null) {
            throw new NullPointerException("Team must not be null!");
        }
        LOGGER.info("Getting current record for " + team.getName());
        return getRecordForTeam(currentMatches);
    }

    public TeamRecord getCurrentDivisionRecord() throws VSTrophyException {
        if (team == null) {
            throw new NullPointerException("Team must not be null!");
        }
        LOGGER.info("Getting current division record for " + team);
        List<Match> matches = new ArrayList<>(currentMatches);
        Iterator<Match> itr = matches.iterator();
        while (itr.hasNext()) {
            Match match = itr.next();
            if (!match.getFirstTeam().getDivision().equals(match.getSecondTeam().getDivision())) {
                itr.remove();
            }
        }
        return getRecordForTeam(matches);
    }

    private TeamRecord getRecordForTeam(List<Match> matches) throws VSTrophyException {
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

    public NumericStatisticPoint getCurrentSeasonPointsForTeam() throws VSTrophyException {
        double totalPoints = 0.0;
        for (Match match : currentMatches) {
            if (match.getFirstTeam().equals(team)) {
                totalPoints += match.getFirstTeamPoints();
                continue;
            }
            if (match.getSecondTeam().equals(team)) {
                totalPoints += match.getSecondTeamPoints();
                continue;
            }
            throw new VSTrophyException("Team " + team.getName() + " did not participate in match " + match.getId());
        }
        return new NumericStatisticPoint(totalPoints);
    }

    public NumericStatisticPoint getPointsForTeam(Team team) throws VSTrophyException {
        double totalPoints = 0.0;
        for (Match match : allMatches) {
            if (match.getFirstTeam().equals(team)) {
                totalPoints += match.getFirstTeamPoints();
                continue;
            }
            if (match.getSecondTeam().equals(team)) {
                totalPoints += match.getSecondTeamPoints();
                continue;
            }
            throw new VSTrophyException("Team " + team.getName() + " did not participate in match " + match.getId());
        }

        return new NumericStatisticPoint(totalPoints);
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
            }
        }
        return modifiedMatches;
    }

    private List<Match> removeMatchesFromOtherSeasonsAndFuture(List<Match> matches) {
        List<Match> modifiedMatches = new ArrayList<>(matches);
        for (Match match : matches) {
            if (match.getWeek().getSeason() == weekInfo.getCurrentSeasonNumber() && match.getWeek().getNumber() < weekInfo.getCurrentWeekNumber()) {
                continue;
            }
            modifiedMatches.remove(match);
        }
        return modifiedMatches;
    }

}

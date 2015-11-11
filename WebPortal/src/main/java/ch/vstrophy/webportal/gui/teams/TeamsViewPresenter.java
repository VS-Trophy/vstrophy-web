/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamEntityManager;
import ch.vstrophy.exception.VSTrophyException;
import ch.vstrophy.statistic.NumericStatisticPoint;
import ch.vstrophy.statistic.StatisticCategory;
import ch.vstrophy.statistic.StatisticPoint;
import ch.vstrophy.statistic.TeamRecord;
import ch.vstrophy.statistic.TeamStatisticProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(TeamsView.class)
public class TeamsViewPresenter extends AbstractMVPPresenter<TeamsView> {

    @Inject
    private TeamEntityManager teamEntityManager;

    @Inject
    private TeamStatisticProvider teamStatisticProvider;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeamsViewPresenter.class);

    @Override
    public void viewEntered() {
        LOGGER.info("Team view entered");
        List<Team> teamList = teamEntityManager.getAllTeams();
        Collections.sort(teamList, new Comparator<Team>() {

            @Override
            public int compare(Team o1, Team o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Map<String, List<StatisticCategory>> records = new HashMap<>();
        for (Team team : teamList) {
            LOGGER.info("Getting statistics for " + team.getName() + "...");
            try {

                teamStatisticProvider.setTeam(team);
                List<StatisticCategory> categories = new ArrayList<>();
                StatisticCategory currentCategory = new StatisticCategory("Aktuelle Saison");
                TeamRecord currentRecord = teamStatisticProvider.getCurrentRecord();
                currentRecord.setName("Overall");
                currentRecord.setShowPercentage(true);
                currentCategory.addStatisticPoint(currentRecord);

                TeamRecord currentDivisionRecord = teamStatisticProvider.getCurrentDivisionRecord();
                currentDivisionRecord.setName("Divison");
                currentDivisionRecord.setShowPercentage(true);
                currentCategory.addStatisticPoint(currentDivisionRecord);

                NumericStatisticPoint points = teamStatisticProvider.getCurrentSeasonPointsForTeam();
                points.setName("Punkte");
                currentCategory.addStatisticPoint(points);

                StatisticPoint pointsPerGame = new NumericStatisticPoint(points.getValue() / currentRecord.getNumberOfMatches());
                pointsPerGame.setName("Punkte / Spiel");
                currentCategory.addStatisticPoint(pointsPerGame);

                categories.add(currentCategory);
                records.put(team.getName(), categories);

                StatisticCategory historicCategory = new StatisticCategory("Historisch");
                TeamRecord historicRecord = teamStatisticProvider.getTotalRecord();
                historicRecord.setName("Total");
                historicCategory.addStatisticPoint(historicRecord);
                historicRecord.setShowPercentage(true);

                points = teamStatisticProvider.getPointsForTeam(team);
                points.setName("Punkte");
                historicCategory.addStatisticPoint(points);

                pointsPerGame = new NumericStatisticPoint(points.getValue() / historicRecord.getNumberOfMatches());
                pointsPerGame.setName("Punkte / Spiel");
                historicCategory.addStatisticPoint(pointsPerGame);

                categories.add(historicCategory);
                records.put(team.getName(), categories);
                LOGGER.info("... done getting statistics for " + team.getName());
            } catch (VSTrophyException ex) {
                LOGGER.error("Could not get current record of team " + team.getName(), ex);
                records.put(team.getName(), new ArrayList<StatisticCategory>());
            }

        }
        LOGGER.info("Done getting statistics for all teams");
        view.setTeamInfo(teamList, records);
    }

}

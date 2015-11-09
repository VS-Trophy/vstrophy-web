/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamEntityManager;
import ch.vstrophy.exception.VSTrophyException;
import ch.vstrophy.statistic.StatisticCategory;
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
        List<Team> teamList = teamEntityManager.getAllTeams();
        Collections.sort(teamList, new Comparator<Team>() {

            @Override
            public int compare(Team o1, Team o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Map<String, List<StatisticCategory>> records = new HashMap<>();
        for (Team team : teamList) {
            try {
                List<StatisticCategory> categories = new ArrayList<>();
                StatisticCategory currentCategory = new StatisticCategory("Aktuelle Saison");
                TeamRecord currentRecord = teamStatisticProvider.getCurrentRecord(team);
                currentRecord.setName("Overall");
                currentRecord.setShowPercentage(true);
                currentCategory.addStatisticPoint(currentRecord);

                TeamRecord currentDivisionRecord = teamStatisticProvider.getCurrentDivisionRecord(team);
                currentDivisionRecord.setName("Divison");
                currentDivisionRecord.setShowPercentage(true);
                currentCategory.addStatisticPoint(currentDivisionRecord);

                categories.add(currentCategory);
                records.put(team.getName(), categories);

                StatisticCategory historicCategory = new StatisticCategory("Historisch");
                TeamRecord historicRecord = teamStatisticProvider.getTotalRecord(team);
                historicRecord.setName("Total");
                historicCategory.addStatisticPoint(historicRecord);
                historicRecord.setShowPercentage(true);
                categories.add(historicCategory);
                records.put(team.getName(), categories);
            } catch (VSTrophyException ex) {
                LOGGER.error("Could not get current record of team " + team.getName(), ex);
                records.put(team.getName(), new ArrayList<StatisticCategory>());
            }
        }
        view.setTeamInfo(teamList, records);
    }

}

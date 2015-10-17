/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.teams;

import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.entities.teams.TeamEntityManager;
import ch.vstrophy.exception.VSTrophyException;
import ch.vstrophy.statistic.TeamRecord;
import ch.vstrophy.statistic.TeamStatisticProvider;
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
        Map<String, TeamRecord> records = new HashMap<>();
        for (Team team : teamList) {
            try {
                records.put(team.getName(), teamStatisticProvider.getCurrentRecord(team));
            } catch (VSTrophyException ex) {
                LOGGER.error("Could not get current record of team " + team.getName(), ex);
                records.put(team.getName(), new TeamRecord());
            }
        }
        view.setTeamInfo(teamList, records);
    }

}

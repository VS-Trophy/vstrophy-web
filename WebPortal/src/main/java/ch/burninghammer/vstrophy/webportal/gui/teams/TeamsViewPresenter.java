/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.teams;

import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.entities.teams.TeamEntityManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(TeamsView.class)
public class TeamsViewPresenter extends AbstractMVPPresenter<TeamsView> {

    @Inject
    private TeamEntityManager teamEntityManager;

    @Override
    public void viewEntered() {
        List<Team> teamList = teamEntityManager.getAllTeams();
        Collections.sort(teamList, new Comparator<Team>() {

            @Override
            public int compare(Team o1, Team o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        view.setTeamList(teamList);
    }

}

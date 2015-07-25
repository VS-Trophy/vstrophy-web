/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teams;

import ch.burninghammer.vstrophy.webportal.entities.teams.TeamEntityManager;
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
        view.setTeamList(teamEntityManager.getAllTeams());
    }

}

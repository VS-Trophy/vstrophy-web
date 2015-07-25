/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teameditor;

import ch.burninghammer.vstrophy.webportal.entities.teams.Team;
import ch.burninghammer.vstrophy.webportal.entities.teams.TeamEntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(TeamEditorView.class)
public class TeamEditorViewPresenter extends AbstractMVPPresenter<TeamEditorView> {

    @Inject
    private TeamEntityManager entityManager;

    @Override
    public void viewEntered() {
        view.showTeamList(entityManager.getAllTeams());
    }

    protected void teamSelected(@Observes @CDIEvent(TeamEditorCDIEvents.TEAM_SELECTED) final ParameterDTO parameters) {
        try {
            Team team = parameters.getPrimaryParameter(Team.class);
            view.showTeam(team);
        } catch (ClassCastException ex) {
            Logger.getGlobal().log(Level.WARNING, "Could not cast selected team");
        }
    }

    protected void saveTeam(@Observes @CDIEvent(TeamEditorCDIEvents.TEAM_CHANGED) final ParameterDTO parameters) {
        try {
            Team team = parameters.getPrimaryParameter(Team.class);
            entityManager.saveTeam(team);
        } catch (ClassCastException ex) {
            Logger.getGlobal().log(Level.WARNING, "Could not cast selected team");
        }
        view.showTeamList(entityManager.getAllTeams());
    }

    protected void addTeam(@Observes @CDIEvent(TeamEditorCDIEvents.TEAM_ADD) final ParameterDTO parameters) {
        Team team = new Team();
        view.showTeam(team);
    }
}

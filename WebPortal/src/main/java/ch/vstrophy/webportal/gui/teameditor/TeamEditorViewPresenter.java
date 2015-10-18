/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teameditor;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamEntityManager;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(TeamEditorView.class)
public class TeamEditorViewPresenter extends AbstractMVPPresenter<TeamEditorView> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeamEditorViewPresenter.class);
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
            LOGGER.warn("Could not cast selected team");
        }
    }

    protected void saveTeam(@Observes @CDIEvent(TeamEditorCDIEvents.TEAM_CHANGED) final ParameterDTO parameters) {
        try {
            Team team = parameters.getPrimaryParameter(Team.class);
            entityManager.saveTeam(team);
        } catch (ClassCastException ex) {
            LOGGER.warn("Could not cast selected team");
        }
        view.showTeamList(entityManager.getAllTeams());
    }

    protected void addTeam(@Observes @CDIEvent(TeamEditorCDIEvents.TEAM_ADD) final ParameterDTO parameters) {
        Team team = new Team();
        view.showTeam(team);
    }
}

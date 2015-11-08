/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teameditor;

import ch.vstrophy.entities.teams.Team;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface TeamEditorView extends MVPView {

    void showTeamList(List<Team> teams);

    void showTeam(Team team);
}
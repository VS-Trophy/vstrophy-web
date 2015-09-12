/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.teams;

import ch.burninghammer.vstrophy.entities.teams.Team;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface TeamsView extends MVPView {

    void setTeamList(List<Team> teams);
}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.statistic.StatisticCategory;
import java.util.List;
import java.util.Map;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface TeamsView extends MVPView {

    void setTeamInfo(List<Team> teams, Map<String, List<StatisticCategory>> records);

}

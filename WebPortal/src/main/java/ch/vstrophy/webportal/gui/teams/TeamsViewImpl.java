/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.statistic.StatisticCategory;
import ch.vstrophy.webportal.gui.teams.component.TeamComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.TabSheetProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamsViewImpl extends AbstractMVPView implements TeamsView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;

    @Inject
    @HorizontalLayoutProperties(width = "100%", margin = true)
    private HorizontalLayout mainLayout;

    @Inject
    @TabSheetProperties(sizeFull = true)
    private TabSheet tabs;

    @Inject
    private Instance<TeamComponent> teamComponentInstance;

    @PostConstruct
    private void init() {
        setCompositionRoot(mainPanel);
        setSizeFull();
        mainPanel.setContent(mainLayout);
        mainLayout.addComponent(tabs);
    }

    @Override
    public void setTeamInfo(final List<Team> teams, final Map<String, List<StatisticCategory>> records) {
        tabs.removeAllComponents();
        for (Team team : teams) {
            TeamComponent comp = teamComponentInstance.get();
            comp.setTeam(team);
            comp.setStatistic(records.get(team.getName()));
            comp.setSizeFull();
            tabs.addTab(comp, team.getName());
        }
    }

}
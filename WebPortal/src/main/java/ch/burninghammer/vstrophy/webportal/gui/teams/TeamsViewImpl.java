/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.teams;

import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.webportal.gui.teams.component.TeamComponent;
import ch.vstrophy.statistic.TeamRecord;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
    @HorizontalLayoutProperties(sizeFull = true, margin = true)
    private HorizontalLayout mainLayout;

    @Inject
    @TabSheetProperties(sizeFull = true)
    private TabSheet tabs;

    @PostConstruct
    private void init() {
        setCompositionRoot(mainPanel);
        setSizeFull();
        mainPanel.setContent(mainLayout);
        mainLayout.addComponent(tabs);
    }

    @Override
    public void setTeamInfo(final List<Team> teams, final Map<String, TeamRecord> records) {
        tabs.removeAllComponents();
        for (Team team : teams) {
            tabs.addTab(new TeamComponent(team, records.get(team.getName())), team.getName());
        }
    }

}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teams;

import ch.burninghammer.vstrophy.webportal.entities.teams.Team;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
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
    @HorizontalLayoutProperties(width = "100%", margin = true)
    private HorizontalLayout mainLayout;

    @Inject
    @TabSheetProperties
    private TabSheet tabs;

    @PostConstruct
    private void init() {
        setCompositionRoot(mainPanel);

        mainPanel.setContent(mainLayout);
        mainLayout.addComponent(tabs);
    }

    @Override
    public void setTeamList(final List<Team> teams) {
        tabs.removeAllComponents();
        for (Team team : teams) {
            tabs.addTab(new VerticalLayout(), team.getName());
        }
    }
}

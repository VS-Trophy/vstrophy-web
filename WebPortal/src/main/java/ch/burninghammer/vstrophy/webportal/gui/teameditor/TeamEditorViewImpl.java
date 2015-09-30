/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.teameditor;

import ch.burninghammer.vstrophy.entities.teams.Team;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.TableProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamEditorViewImpl extends AbstractMVPView implements TeamEditorView {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeamEditorViewImpl.class);
    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;
    @Inject
    @HorizontalLayoutProperties(width = "100%", margin = true)
    private HorizontalLayout mainLayout;
    @Inject
    @TableProperties(immediate = true, sizeUndefined = true, pageLength = 10)
    private Table teamTable;

    @Inject
    @VerticalLayoutProperties(sizeUndefined = true, height = "80%")
    private VerticalLayout tableLayout;

    @Inject
    @ButtonProperties(caption = "Neu")
    private Button newTeamButton;

    @Inject
    private TeamForm form;

    @Override
    public void showTeamList(List<Team> teams) {
        BeanItemContainer<Team> teamBeanContainer = new BeanItemContainer<>(Team.class);
        teamBeanContainer.addAll(teams);
        try {
            teamTable.setContainerDataSource(teamBeanContainer);
        } catch (Table.CacheUpdateException ex) {
            LOGGER.warn("Error during team table cache update. Ignoring as this might happen with incomplete teams.");
        }
        teamTable.setVisibleColumns("name");
        newTeamButton.setEnabled(true);
        mainLayout.removeComponent(form);
    }

    @PostConstruct
    private void initView() {
        setSizeFull();
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        teamTable.addValueChangeListener(new TeamListValueChangedListener());
        teamTable.setSelectable(true);
        tableLayout.addComponent(teamTable);
        tableLayout.addComponent(newTeamButton);
        newTeamButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                event.getButton().setEnabled(false);
                fireViewEvent(TeamEditorCDIEvents.TEAM_ADD, null);
            }
        });
        mainLayout.addComponent(tableLayout);

    }

    @Override
    public void showTeam(Team team) {
        mainLayout.addComponent(form);
        mainLayout.setExpandRatio(form, 0.8f);
        form.bindTeam(team);
    }

    private class TeamListValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty().getValue() != null) {
                BeanItem<Team> item = (BeanItem) teamTable.getItem(event.getProperty().getValue());
                fireViewEvent(TeamEditorCDIEvents.TEAM_SELECTED, item.getBean());
            }
        }
    }
}

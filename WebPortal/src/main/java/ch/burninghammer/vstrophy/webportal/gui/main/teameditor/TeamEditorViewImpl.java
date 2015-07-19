/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teameditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import ch.burninghammer.vstrophy.webportal.entities.teams.Team;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
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

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;
    @Inject
    @HorizontalLayoutProperties(sizeFull = true, margin = true)
    private HorizontalLayout mainLayout;
    @Inject
    @TableProperties(immediate = true, sizeUndefined = true)
    private Table teamTable;

    @Inject
    @VerticalLayoutProperties(sizeUndefined = true, height = "100%")
    private VerticalLayout tableLayout;

    @Inject
    @ButtonProperties(caption = "Neu")
    private Button newTeamButton;

    @Inject
    private TeamForm form;

    @Override
    public void showTeamList(List<Team> teams) {
        BeanContainer<Integer, Team> TeamBeanContainer = new BeanContainer<>(Team.class);
        TeamBeanContainer.setBeanIdProperty("id");
        TeamBeanContainer.addAll(teams);
        teamTable.setContainerDataSource(TeamBeanContainer);
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
        form.bindTeam(team);
    }

    private class TeamListValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty().getValue() != null) {
                BeanItem<NewsItem> item = (BeanItem) teamTable.getItem(event.getProperty().getValue());
                fireViewEvent(TeamEditorCDIEvents.TEAM_SELECTED, item.getBean());
            }
        }
    }
}

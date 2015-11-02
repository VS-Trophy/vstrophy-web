/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams.component;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.statistic.StatisticCategory;
import ch.vstrophy.webportal.gui.statistics.TeamStatisticComponent;
import ch.vstrophy.webportal.gui.theme.VSTrophyTheme;
import ch.vstrophy.webportal.util.LogoHelper;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.ImageProperties;
import org.vaadin.addon.cdiproperties.annotation.LabelProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Dependent
public class TeamComponent extends ViewComponent {

    @Inject
    @LabelProperties(sizeUndefined = true, styleName = VSTrophyTheme.LABEL_HUGE)
    private Label teamNameLabel;

    @Inject
    @LabelProperties(caption = "Ort:", styleName = VSTrophyTheme.LABEL_BOLD)
    private Label cityLabel;

    @Inject
    @LabelProperties(caption = "Stadion:", styleName = VSTrophyTheme.LABEL_BOLD)
    private Label stadiumLabel;

    @Inject
    @ImageProperties(height = "40px")
    private Image logoImage;

    @Inject
    @PanelProperties()
    private Panel uniformPanel;

    @Inject
    @ImageProperties(height = "110px")
    private Image uniformImage;

    @Inject
    @LabelProperties(caption = "Farben:", styleName = VSTrophyTheme.LABEL_BOLD)
    private Label colorsLabel;

    @Inject
    @LabelProperties(caption = "Fans:", styleName = VSTrophyTheme.LABEL_BOLD)
    private Label fansLabel;

    @Inject
    @LabelProperties(caption = "Gegründet:", styleName = VSTrophyTheme.LABEL_BOLD)
    private Label foundedLabel;

    @Inject
    @LabelProperties(caption = "VS-Trophy Mitglied seit:", styleName = VSTrophyTheme.LABEL_BOLD)
    private Label joinedLabel;

    @Inject
    @FormLayoutProperties(spacing = false, margin = false, sizeUndefined = true)
    private FormLayout leftFormLayout;

    @Inject
    @FormLayoutProperties(spacing = false, margin = false, sizeUndefined = true)
    private FormLayout rightFormLayout;

    @Inject
    @VerticalLayoutProperties(width = "100%", margin = true)
    private VerticalLayout mainLayout;

    @Inject
    @HorizontalLayoutProperties(spacing = true)
    private HorizontalLayout titleLayout;

    @Inject
    @HorizontalLayoutProperties(spacing = true)
    private HorizontalLayout factsLayout;

    @Inject
    @HorizontalLayoutProperties(spacing = true)
    private HorizontalLayout detailsLayout;

    @Inject
    @PanelProperties(caption = "Statistik")
    private Panel statisticPanel;

    @Inject
    @PanelProperties(caption = "Funktionäre")
    private Panel officialsPanel;

    @Inject
    private OfficialsComponent officialsComponent;

    @Inject
    private TeamStatisticComponent statisticsComponent;

    public void setTeam(Team team) {
        bindTeam(team);
    }

    public void setStatistic(List<StatisticCategory> statistics) {
        statisticsComponent.setStatistics(statistics);
    }

    private void bindTeam(Team team) {
        teamNameLabel.setValue(team.getName());
        cityLabel.setValue(team.getCity());
        stadiumLabel.setValue(team.getStadium());
        colorsLabel.setValue(team.getColors());
        if ("".equals(team.getFans())) {
            fansLabel.setVisible(false);
        } else {
            fansLabel.setValue(team.getFans());
            fansLabel.setVisible(true);
        }

        foundedLabel.setValue(new DateTime(team.getFoundedIn()).getYear() + "");
        joinedLabel.setValue(new DateTime(team.getJoinedIn()).getYear() + "");
        logoImage.setSource(LogoHelper.createLogoResource(team.getLogo(), team.getName() + "_logo"));
        uniformImage.setSource(LogoHelper.createLogoResource(team.getUniformPicture(), team.getName() + "_uniform"));
        officialsComponent.setTeam(team);
    }

    @PostConstruct
    private void init() {
        setCompositionRoot(mainLayout);

        mainLayout.addComponent(titleLayout);
        mainLayout.addComponent(factsLayout);
        mainLayout.addComponent(new Label("<hr/>", ContentMode.HTML));
        mainLayout.addComponent(detailsLayout);

        mainLayout.setExpandRatio(detailsLayout, 0.8f);

        titleLayout.addComponent(logoImage);
        titleLayout.addComponent(teamNameLabel);
        titleLayout.setComponentAlignment(teamNameLabel, Alignment.MIDDLE_CENTER);

        factsLayout.addComponent(leftFormLayout);
        factsLayout.addComponent(rightFormLayout);
        factsLayout.addComponent(uniformPanel);

        leftFormLayout.addComponent(cityLabel);
        leftFormLayout.addComponent(stadiumLabel);
        leftFormLayout.addComponent(colorsLabel);

        rightFormLayout.addComponent(foundedLabel);
        rightFormLayout.addComponent(joinedLabel);
        rightFormLayout.addComponent(fansLabel);

        uniformPanel.setContent(uniformImage);

        detailsLayout.addComponent(statisticPanel);
        statisticPanel.setContent(statisticsComponent);
        detailsLayout.addComponent(officialsPanel);
        officialsPanel.setContent(officialsComponent);
    }

}

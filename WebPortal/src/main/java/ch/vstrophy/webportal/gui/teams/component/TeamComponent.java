/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams.component;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamOfficial;
import ch.vstrophy.statistic.StatisticCategory;
import ch.vstrophy.webportal.gui.statistics.TeamStatisticComponent;
import ch.vstrophy.webportal.gui.theme.VSTrophyTheme;
import ch.vstrophy.webportal.util.LogoHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.vaadin.addon.cdimvp.ViewComponent;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamComponent extends ViewComponent {

    private final Panel mainPanel;
    private final VerticalLayout mainLayout;
    private final HorizontalLayout masterContentLayout;
    private final FormLayout leftContentLayout;
    private final VerticalLayout rightContentLayout;
    private final Team team;
    private Label teamNameTitle;
    private Label cityLabel;
    private Label stadiumLabel;
    private Label coloursLabel;
    private Label fansLabel;
    private Label foundedInLabel;
    private Label joinedInLabel;
    private Label divisionLabel;
    private Label recordLabel;
    private Image logoImage;
    private Image uniformImage;

    public TeamComponent(final Team team, List<StatisticCategory> statistics) {
        this.team = team;
        mainPanel = new Panel();

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainPanel.setContent(mainLayout);
        mainPanel.setStyleName(ValoTheme.PANEL_BORDERLESS);
        setCompositionRoot(mainPanel);
        masterContentLayout = new HorizontalLayout();
        masterContentLayout.setSizeFull();
        leftContentLayout = new FormLayout();
        leftContentLayout.setSizeUndefined();
        rightContentLayout = new VerticalLayout();
        rightContentLayout.setSizeUndefined();

        masterContentLayout.addComponent(leftContentLayout);
        masterContentLayout.addComponent(rightContentLayout);
        TeamStatisticComponent statisticComponent = new TeamStatisticComponent(statistics);
        statisticComponent.setSizeUndefined();
        Panel statisticPanel = new Panel("Statistik");
        statisticPanel.addStyleName(VSTrophyTheme.PANEL_WELL);
        statisticPanel.setContent(statisticComponent);
        statisticPanel.setSizeUndefined();
        statisticPanel.setHeight("100%");
        masterContentLayout.addComponent(statisticPanel);
        createFacts();
        createOfficials();
        createImages();
        mainLayout.addComponent(createTitle());
        mainLayout.addComponent(masterContentLayout);
        mainLayout.addComponent(createFooter());
        mainLayout.setExpandRatio(masterContentLayout, 0.7f);
        masterContentLayout.setComponentAlignment(leftContentLayout, Alignment.TOP_CENTER);
        masterContentLayout.setComponentAlignment(rightContentLayout, Alignment.TOP_CENTER);

    }

    private void createFacts() {

        cityLabel = new TeamComponentLabel(team.getCity());
        cityLabel.setCaption("Ort:");
        cityLabel.addStyleName(ValoTheme.LABEL_BOLD);
        cityLabel.addStyleName(ValoTheme.LABEL_LARGE);
        leftContentLayout.addComponent(cityLabel);

        stadiumLabel = new TeamComponentLabel(team.getStadium());
        stadiumLabel.setCaption("Stadion:");
        stadiumLabel.addStyleName(ValoTheme.LABEL_BOLD);
        stadiumLabel.addStyleName(ValoTheme.LABEL_LARGE);
        leftContentLayout.addComponent(stadiumLabel);

        coloursLabel = new TeamComponentLabel(team.getColors());
        coloursLabel.setCaption("Farben:");
        coloursLabel.addStyleName(ValoTheme.LABEL_BOLD);
        coloursLabel.addStyleName(ValoTheme.LABEL_LARGE);
        leftContentLayout.addComponent(coloursLabel);

        if (team.getFans() != null && !team.getFans().equals("")) {
            fansLabel = new TeamComponentLabel(team.getFans());
            fansLabel.setCaption("Fans:");
            fansLabel.addStyleName(ValoTheme.LABEL_BOLD);
            fansLabel.addStyleName(ValoTheme.LABEL_LARGE);
            leftContentLayout.addComponent(fansLabel);
        }
    }

    private Component createTitle() {
        VerticalLayout titleLayout = new VerticalLayout();
        teamNameTitle = new Label(team.getName());
        teamNameTitle.addStyleName(ValoTheme.LABEL_H1);
        teamNameTitle.setSizeUndefined();
        divisionLabel = new Label((team.getDivision() == null ? "Keine" : team.getDivision().getName()) + " Division");
        divisionLabel.addStyleName(ValoTheme.LABEL_H3);
        divisionLabel.setSizeUndefined();

        titleLayout.addComponent(teamNameTitle);
        titleLayout.setComponentAlignment(teamNameTitle, Alignment.MIDDLE_CENTER);
        titleLayout.addComponent(divisionLabel);

        titleLayout.setComponentAlignment(divisionLabel, Alignment.MIDDLE_CENTER);

        return titleLayout;
    }

    private Component createFooter() {
        HorizontalLayout footerLayout = new HorizontalLayout();
        footerLayout.setWidth("100%");
        foundedInLabel = new TeamComponentDateLabel(team.getFoundedIn(), "Gegründet in ");
        joinedInLabel = new TeamComponentDateLabel(team.getJoinedIn(), "VS-Trophy Mitglied seit ");
        footerLayout.addComponent(foundedInLabel);
        footerLayout.addComponent(joinedInLabel);
        footerLayout.setComponentAlignment(foundedInLabel, Alignment.MIDDLE_CENTER);
        footerLayout.setComponentAlignment(joinedInLabel, Alignment.MIDDLE_CENTER);
        return footerLayout;
    }

    private void createOfficials() {
        leftContentLayout.addComponent(new Label(""));
        for (TeamOfficial official : team.getOfficials()) {
            Label officialLabel = new TeamComponentLabel(official.getName());
            officialLabel.setSizeUndefined();
            officialLabel.setCaption(official.getFunction() + ":");

            leftContentLayout.addComponent(officialLabel);
        }
    }

    private void createImages() {
        logoImage = new Image("Logo: ", LogoHelper.createLogoResource(team.getLogo(), team.getName() + "logo" + System.currentTimeMillis()));
        rightContentLayout.addComponent(logoImage);
        uniformImage = new Image("Uniform: ", LogoHelper.createLogoResource(team.getUniformPicture(), team.getName() + "uniform" + System.currentTimeMillis()));
        rightContentLayout.addComponent(uniformImage);
    }

    private class TeamComponentLabel extends Label {

        public TeamComponentLabel(String content) {
            super(content);
            addStyleName(ValoTheme.LABEL_BOLD);
            addStyleName(ValoTheme.LABEL_LARGE);

        }
    }

    private class TeamComponentDateLabel extends Label {

        public TeamComponentDateLabel(Date date, String caption) {
            super();
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            setValue(caption + " " + format.format(date));
            addStyleName(ValoTheme.LABEL_LIGHT);
            addStyleName(ValoTheme.LABEL_SMALL);
            setSizeUndefined();
        }
    }

}

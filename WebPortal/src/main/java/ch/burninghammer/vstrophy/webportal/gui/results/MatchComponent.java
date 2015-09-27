/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.results;

import ch.burninghammer.vstrophy.entities.match.Match;
import ch.burninghammer.vstrophy.webportal.gui.theme.VSTrophyTheme;
import ch.burninghammer.vstrophy.webportal.util.LogoHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class MatchComponent extends CustomComponent {

    public MatchComponent(Match match) {
        Panel mainPanel = new Panel();
        mainPanel.setSizeFull();
        mainPanel.addStyleName(VSTrophyTheme.PANEL_WELL);
        GridLayout mainLayout = new GridLayout(7, 1);
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setColumnExpandRatio(0, 2);
        mainLayout.setColumnExpandRatio(1, 1);
        mainLayout.setColumnExpandRatio(2, 1);
        mainLayout.setColumnExpandRatio(3, 1);
        mainLayout.setColumnExpandRatio(4, 1);
        mainLayout.setColumnExpandRatio(5, 1);
        mainLayout.setColumnExpandRatio(6, 2);

        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        mainLayout.setSpacing(true);
        mainLayout.setSizeFull();
        setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);

        Image firstTeamLogo = new Image();
        firstTeamLogo.setSource(LogoHelper.createLogoResource(match.getFirstTeam().getLogo(), match.getFirstTeam().getName() + "Logo"));
        Image secondTeamLogo = new Image();
        secondTeamLogo.setSource(LogoHelper.createLogoResource(match.getSecondTeam().getLogo(), match.getSecondTeam().getName() + "Logo"));
        Label firstTeamLabel = new Label(match.getFirstTeam().getName());
        Label secondTeamLabel = new Label(match.getSecondTeam().getName());
        Label firstTeamPoints = new Label(String.valueOf(match.getFirstTeamPoints()));
        Label secondTeamPoints = new Label(String.valueOf(match.getSecondTeamPoints()));
        Label vsLabel = new Label("VS");
        vsLabel.setWidthUndefined();

        //By checking for >= and <= both teams are bold if there is a draw
        if (match.getFirstTeamPoints() >= match.getSecondTeamPoints()) {
            firstTeamLabel.addStyleName(VSTrophyTheme.LABEL_BOLD);
            firstTeamPoints.addStyleName(VSTrophyTheme.LABEL_BOLD);
        }
        if (match.getFirstTeamPoints() <= match.getSecondTeamPoints()) {
            secondTeamLabel.addStyleName(VSTrophyTheme.LABEL_BOLD);
            secondTeamPoints.addStyleName(VSTrophyTheme.LABEL_BOLD);
        }

        firstTeamLogo.setWidth("50px");
        secondTeamLogo.setWidth("50px");

        mainLayout.addComponent(firstTeamLabel, 0, 0);
        mainLayout.addComponent(firstTeamLogo, 1, 0);
        mainLayout.addComponent(firstTeamPoints, 2, 0);
        mainLayout.addComponent(vsLabel, 3, 0);
        mainLayout.addComponent(secondTeamPoints, 4, 0);
        mainLayout.addComponent(secondTeamLogo, 5, 0);
        mainLayout.addComponent(secondTeamLabel, 6, 0);

        mainLayout.setComponentAlignment(firstTeamLogo, Alignment.MIDDLE_RIGHT);
        mainLayout.setComponentAlignment(vsLabel, Alignment.MIDDLE_CENTER);
        mainLayout.setComponentAlignment(secondTeamLogo, Alignment.MIDDLE_LEFT);
        mainLayout.setComponentAlignment(firstTeamLabel, Alignment.MIDDLE_RIGHT);
        mainLayout.setComponentAlignment(secondTeamLabel, Alignment.MIDDLE_LEFT);
    }

}

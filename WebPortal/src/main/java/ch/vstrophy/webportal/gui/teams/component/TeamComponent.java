/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams.component;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.webportal.gui.theme.VSTrophyTheme;
import ch.vstrophy.webportal.util.LogoHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.ImageProperties;
import org.vaadin.addon.cdiproperties.annotation.LabelProperties;
import org.vaadin.addon.cdiproperties.annotation.TabSheetProperties;
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
    @LabelProperties(caption = "Ort:")
    private Label cityLabel;

    @Inject
    @LabelProperties(caption = "Stadion:")
    private Label stadiumLabel;

    @Inject
    @ImageProperties(height = "50px")
    private Image logoImage;

    @Inject
    @LabelProperties(caption = "Farben:")
    private Label colorsLabel;

    @Inject
    @LabelProperties(caption = "Fans:")
    private Label fansLabel;

    @Inject
    @FormLayoutProperties
    private FormLayout leftFormLayout;

    @Inject
    @FormLayoutProperties
    private FormLayout rightFormLayout;

    @Inject
    @VerticalLayoutProperties(width = "100%")
    private VerticalLayout mainLayout;

    @Inject
    @HorizontalLayoutProperties(spacing = true, margin = true)
    private HorizontalLayout titleLayout;

    @Inject
    @HorizontalLayoutProperties(width = "100%")
    private HorizontalLayout factsLayout;

    @Inject
    @TabSheetProperties
    private TabSheet infoTab;

    public void setTeam(Team team) {
        bindTeam(team);
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

        logoImage.setSource(LogoHelper.createLogoResource(team.getLogo(), team.getName() + "logo"));

    }

    @PostConstruct
    private void init() {
        setCompositionRoot(mainLayout);

        mainLayout.addComponent(titleLayout);
        mainLayout.addComponent(factsLayout);
        mainLayout.addComponent(infoTab);

        titleLayout.addComponent(logoImage);
        titleLayout.addComponent(teamNameLabel);
        titleLayout.setComponentAlignment(teamNameLabel, Alignment.MIDDLE_CENTER);

        factsLayout.addComponent(leftFormLayout);
        factsLayout.addComponent(rightFormLayout);

        rightFormLayout.addComponent(cityLabel);
        leftFormLayout.addComponent(stadiumLabel);
        rightFormLayout.addComponent(colorsLabel);
        leftFormLayout.addComponent(fansLabel);
    }

}

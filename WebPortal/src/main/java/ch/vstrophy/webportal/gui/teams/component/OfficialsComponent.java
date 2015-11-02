/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.teams.component;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamOfficial;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class OfficialsComponent extends ViewComponent {

    @Inject
    @FormLayoutProperties(margin = true)
    private FormLayout formLayout;

    @PostConstruct
    private void init() {
        setCompositionRoot(formLayout);
    }

    public void setTeam(Team team) {
        for (TeamOfficial official : team.getOfficials()) {
            Label label = new Label(official.getName());
            label.setCaption(official.getFunction());
            formLayout.addComponent(label);
        }
    }
}

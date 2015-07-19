/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.login;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import ch.burninghammer.vstrophy.webportal.gui.main.MainMenuCDIEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.LabelProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class LoggedInUserForm extends ViewComponent {

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout formLayout;

    @Inject
    @LabelProperties(caption = "Eingeloggt als ")
    private Label userNameLabel;

    @Inject
    @ButtonProperties(caption = "Ausloggen")
    private Button logoutButton;

    private User user;

    @PostConstruct
    private void init() {
        this.setCompositionRoot(formLayout);
        formLayout.addComponent(userNameLabel);
        formLayout.addComponent(logoutButton);
        logoutButton.addClickListener(new LogoutClickListener());
    }

    public void setUser(User user) {
        this.user = user;
        userNameLabel.setValue(user.getName());
    }

    private class LogoutClickListener implements ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent event) {
            fireViewEvent(MainMenuCDIEvents.LOGOUT_CLICKED, null);
        }

    }

}

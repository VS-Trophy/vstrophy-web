/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.login;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class LoginButtonComponent extends ViewComponent implements LoginProvider.LoginChangeListener {

    @Inject
    private LoginProvider loginProvider;

    private PopupView loginPopup;

    private PopupView loggedInPopup;

    @Inject
    private LoginForm loginForm;

    @Inject
    private LoggedInUserForm loggedInUserForm;

    @PostConstruct
    private void init() {
        loginProvider.addLoginChangeListener(this);
        loginPopup = new PopupView("Einloggen", loginForm);
        loggedInPopup = new PopupView("Eingeloggt", loggedInUserForm);
        loggedInPopup.setHideOnMouseOut(false);
        loginPopup.setHideOnMouseOut(false);
        this.setCompositionRoot(loginPopup);
        setWidthUndefined();

        loginChanged(loginProvider.getUser());
    }

    @Override
    public void loginChanged(final User newUser) {

        if (newUser == null) {
            this.setCompositionRoot(loginPopup);
        } else {
            loggedInUserForm.setUser(newUser);
            loggedInPopup.setContent(new PopupView.Content() {

                @Override
                public String getMinimizedValueAsHTML() {
                    return "User: " + newUser.getName();
                }

                @Override
                public Component getPopupComponent() {
                    return loggedInUserForm;
                }
            });
            this.setCompositionRoot(loggedInPopup);
        }
    }

}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.useradministration;

import ch.burninghammer.vstrophy.entities.user.User;
import ch.burninghammer.vstrophy.entities.user.UserEntityManager;
import ch.burninghammer.vstrophy.webportal.error.WebPortalException;
import ch.burninghammer.vstrophy.webportal.gui.security.PasswordUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(UserAdministrationView.class)
public class UserAdministrationPresenter extends AbstractMVPPresenter<UserAdministrationView> {

    @Inject
    UserEntityManager userEntityManager;

    @Inject
    PasswordUtils passwordUtils;

    @Override
    public void viewEntered() {
        view.showUserList(userEntityManager.getAllUsers());
    }

    protected void userSelected(
            @Observes @CDIEvent(UserAdministrationCDIEvents.USER_SELECTED) final ParameterDTO parameters) {
        User user = parameters.getPrimaryParameter(User.class);
        view.showUserDetails(user);
    }

    protected void userChanged(@Observes @CDIEvent(UserAdministrationCDIEvents.USER_CHANGED) final ParameterDTO parameters) {
        User user = parameters.getPrimaryParameter(User.class);
        String password = parameters.getSecondaryParameter(0, String.class);
        try {
            if (password != null && password != "") {
                passwordUtils.setPassword(user, password);
            }
            userEntityManager.saveUser(user);
            view.showUserList(userEntityManager.getAllUsers());
        } catch (WebPortalException ex) {
            Logger.getLogger(UserAdministrationPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void userAdd(@Observes @CDIEvent(UserAdministrationCDIEvents.USER_ADD) final ParameterDTO parameters) {
        User user = new User();
        user.setName("<USERNAME>");
        view.showUserDetails(user);
    }

}

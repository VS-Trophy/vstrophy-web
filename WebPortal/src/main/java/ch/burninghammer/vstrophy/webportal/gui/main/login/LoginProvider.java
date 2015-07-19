/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.login;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@SessionScoped
public class LoginProvider implements Serializable {

    private static final long serialVersionUID = 1;
    private User user;
    private Set<LoginChangeListener> loginChangeListeners;

    @PostConstruct
    private void init() {
        loginChangeListeners = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyLoginChangeListeners(user);
    }

    public void addLoginChangeListener(LoginChangeListener loginChangeListener) {
        if (loginChangeListener != null) {
            loginChangeListeners.add(loginChangeListener);
        }
    }

    private void notifyLoginChangeListeners(User newUser) {
        for (LoginChangeListener listener : loginChangeListeners) {
            listener.loginChanged(newUser);
        }
    }

    public interface LoginChangeListener {

        public void loginChanged(User newUser);
    }

}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import ch.burninghammer.vstrophy.webportal.entities.user.UserEntityManager;
import ch.burninghammer.vstrophy.webportal.gui.main.login.LoginProvider;
import ch.burninghammer.vstrophy.webportal.gui.main.teameditor.TeamEditorView;
import ch.burninghammer.vstrophy.webportal.gui.main.teams.TeamsView;
import ch.burninghammer.vstrophy.webportal.gui.newseditor.NewsEditorView;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedView;
import ch.burninghammer.vstrophy.webportal.gui.useradministration.UserAdministrationView;
import ch.burninghammer.vstrophy.webportal.security.PasswordUtils;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.MVPView;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(MainView.class)
public class MainPresenter extends AbstractMVPPresenter<MainView> implements LoginProvider.LoginChangeListener {

    @Inject
    private Instance<MVPView> views;

    @Inject
    private PasswordUtils passwordUtils;

    @Inject
    private LoginProvider loginProvider;

    @Inject
    private UserEntityManager userEntityManager;

    protected void navigateToNewsFeed(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSFEED) final ParameterDTO parameters) {
        navigateToView(NewsFeedView.class);
    }

    @PostConstruct
    private void init() {
        loginProvider.addLoginChangeListener(this);
    }

    protected void navigateToNewsEditor(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSEDITOR) final ParameterDTO parameters) {
        navigateToView(NewsEditorView.class);
    }

    protected void navigateToUserAdministration(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_USER_ADMINISTRATION) final ParameterDTO parameters) {
        navigateToView(UserAdministrationView.class);
    }

    protected void navigateToTeamEditor(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_TEAM_EDITOR) final ParameterDTO parameters) {
        navigateToView(TeamEditorView.class);
    }

    protected void navigateToTeams(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_TEAMS) final ParameterDTO parameters) {
        navigateToView(TeamsView.class);
    }

    protected void loginClicked(@Observes @CDIEvent(MainMenuCDIEvents.LOGIN_CLICKED) final ParameterDTO parameters) {
        String username = parameters.getPrimaryParameter(String.class);
        String password = parameters.getSecondaryParameter(0, String.class);
        if (passwordUtils.checkCredentials(username, password)) {
            loginProvider.setUser(userEntityManager.getUser(username));
        }
    }

    protected void logoutClicked(@Observes @CDIEvent(MainMenuCDIEvents.LOGOUT_CLICKED) final ParameterDTO parameters) {
        loginProvider.setUser(null);
    }

    private void navigateToView(final Class<? extends MVPView> viewClass) {
        MVPView newView = views.select(viewClass).get();
        view.setView(newView);
        ((AbstractMVPView) newView).enter();
    }

    @Override
    public void viewEntered() {
        navigateToNewsFeed(null);
        loginChanged(loginProvider.getUser());
    }

    @Override
    public void loginChanged(User newUser) {
        if (newUser == null || !newUser.isAdmin()) {
            view.showPublicButtons();
        } else {
            view.showAllButtons();
        }
    }

}

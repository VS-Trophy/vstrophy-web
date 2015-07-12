/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.gui.newseditor.NewsEditorView;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedView;
import ch.burninghammer.vstrophy.webportal.gui.useradministration.UserAdministrationView;
import ch.burninghammer.vstrophy.webportal.security.PasswordUtils;
import com.vaadin.ui.Notification;
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
public class MainPresenter extends AbstractMVPPresenter<MainView> {

    @Inject
    private Instance<MVPView> views;

    @Inject
    private PasswordUtils passwordUtils;

    protected void navigateToNewsFeed(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSFEED) final ParameterDTO parameters) {
        navigateToView(NewsFeedView.class);
    }

    protected void navigateToNewsEditor(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSEDITOR) final ParameterDTO parameters) {
        navigateToView(NewsEditorView.class);
    }

    protected void navigateToUserAdministration(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_USER_ADMINISTRATION) final ParameterDTO parameters) {
        navigateToView(UserAdministrationView.class);
    }

    protected void loginClicked(@Observes @CDIEvent(MainMenuCDIEvents.LOGIN_CLICKED) final ParameterDTO parameters) {
        String username = parameters.getPrimaryParameter(String.class);
        String password = parameters.getSecondaryParameter(0, String.class);
        Notification.show(passwordUtils.checkCredentials(username, password) + "");
    }

    private void navigateToView(final Class<? extends MVPView> viewClass) {
        MVPView newView = views.select(viewClass).get();
        view.setView(newView);
        ((AbstractMVPView) newView).enter();
    }

    @Override
    public void viewEntered() {
        navigateToNewsFeed(null);
    }

}

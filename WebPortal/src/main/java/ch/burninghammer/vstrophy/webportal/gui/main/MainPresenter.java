/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.gui.newseditor.NewsEditorView;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedView;
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

    protected void navigateToNewsFeed(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSFEED) final ParameterDTO parameters) {
        navigateToView(NewsFeedView.class);
    }

    protected void navigateToNewsEditor(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSEDITOR) final ParameterDTO parameters) {
        navigateToView(NewsEditorView.class);
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

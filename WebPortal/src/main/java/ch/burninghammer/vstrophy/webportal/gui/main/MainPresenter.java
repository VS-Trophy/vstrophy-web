/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.gui.newseditor.NewsEditorView;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedView;
import javax.enterprise.event.Observes;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(MainView.class)
public class MainPresenter extends AbstractMVPPresenter<MainView> {

    protected void navigateToNewsFeed(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSFEED) final ParameterDTO parameters) {
        view.setView(NewsFeedView.class);
    }

    protected void navigateToNewsEditor(
            @Observes @CDIEvent(MainMenuCDIEvents.SHOW_NEWSEDITOR) final ParameterDTO parameters) {
        view.setView(NewsEditorView.class);
    }

    @Override
    public void viewEntered() {
        view.setView(NewsFeedView.class);
    }

}

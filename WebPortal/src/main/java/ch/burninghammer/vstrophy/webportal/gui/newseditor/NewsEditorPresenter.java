/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItemEntityManager;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(NewsEditorView.class)
public class NewsEditorPresenter extends AbstractMVPPresenter<NewsEditorView> {

    @Inject
    private NewsItemEntityManager newsItemEntityManager;

    @Override
    public void viewEntered() {
        view.showNewsItemlist(newsItemEntityManager.getAllNewsItems());
    }

    protected void newsItemSelected(
            @Observes @CDIEvent(NewsEditorCDIEvents.newsItemSelected) final ParameterDTO parameters) {
        view.showSelectedNewsItemDetails();
    }

}

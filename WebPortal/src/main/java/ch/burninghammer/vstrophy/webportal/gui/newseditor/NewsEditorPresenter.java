/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
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
        view.showSelectedNewsItemDetails(newsItemEntityManager.getAllNewsItems().get(0));
    }

    protected void newsItemSelected(
            @Observes @CDIEvent(NewsEditorCDIEvents.newsItemSelected) final ParameterDTO parameters) {

    }

    protected void newsItemChanged(@Observes @CDIEvent(NewsEditorCDIEvents.newsItemChanged) final ParameterDTO parameters) {
        NewsItem newsItem = parameters.getPrimaryParameter(NewsItem.class);
        newsItemEntityManager.saveNewsItem(newsItem);
    }
}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import ch.burninghammer.vstrophy.webportal.entities.news.NewsItemEntityManager;
import java.util.Date;
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
            @Observes @CDIEvent(NewsEditorCDIEvents.NEWS_ITEM_SELECTED) final ParameterDTO parameters) {
        NewsItem newsItem = parameters.getPrimaryParameter(NewsItem.class);
        view.showNewsItemDetails(newsItem);
    }

    protected void newsItemChanged(@Observes @CDIEvent(NewsEditorCDIEvents.NEWS_ITEM_CHANGED) final ParameterDTO parameters) {
        NewsItem newsItem = parameters.getPrimaryParameter(NewsItem.class);
        newsItemEntityManager.saveNewsItem(newsItem);
        view.showNewsItemlist(newsItemEntityManager.getAllNewsItems());
    }

    protected void newsItemAdd(@Observes @CDIEvent(NewsEditorCDIEvents.NEWS_ITEM_ADD) final ParameterDTO parameters) {
        NewsItem newsItem = new NewsItem();
        newsItem.setTitle("Titel");
        newsItem.setText("Text");
        newsItem.setPublicationDate(new Date());
        view.showNewsItemDetails(newsItem);
    }
}

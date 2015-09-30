/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed;

import ch.burninghammer.vstrophy.entities.news.NewsItem;
import ch.burninghammer.vstrophy.entities.news.NewsItemEntityManager;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(NewsFeedView.class)
public class NewsFeedPresenter extends AbstractMVPPresenter<NewsFeedView> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NewsFeedPresenter.class);
    @Inject
    private NewsItemEntityManager newsItemEntityManager;

    @Override
    public void viewEntered() {
        LOGGER.info("Newsfeed entered");
        showNewsFeed(null);
    }

    protected void showNewsFeed(@Observes @CDIEvent(NewsFeedCDIEvents.SHOW_NEWS_FEED) final ParameterDTO parameters) {
        view.showNewsItems(newsItemEntityManager.getAllNewsItems());
    }

    protected void newsItemSelected(
            @Observes @CDIEvent(NewsFeedCDIEvents.SHOW_NEWS_ITEM) final ParameterDTO parameters) {
        NewsItem newsItem = parameters.getPrimaryParameter(NewsItem.class);
        view.showNewsItem(newsItem);
    }

}

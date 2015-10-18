/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.newsfeed;

import ch.vstrophy.entities.news.NewsItem;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface NewsFeedView extends MVPView {

    public void showNewsItem(NewsItem newsItem);

    public void showNewsItems(List<NewsItem> newsItemList);
}

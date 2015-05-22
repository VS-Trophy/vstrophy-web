/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import javax.ejb.Stateless;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class NewsItemComponentFactory {

    public NewsItemComponent createNewsItemComponent(NewsItem newsItem) {
        NewsItemComponent component = new NewsItemComponent(newsItem);
        return component;
    }
}

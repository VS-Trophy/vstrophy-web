/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.newsfeed.component;

import ch.vstrophy.entities.news.NewsItem;
import ch.vstrophy.webportal.gui.events.VSTrophyMVPView;
import com.vaadin.ui.Component;
import javax.ejb.Stateless;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class NewsItemComponentFactory {

    public Component createCompactNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        Component component = new FeedNewsItemComponent(newsItem, view);
        component.setWidth("90%");
        return component;
    }

    public Component createFullNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        Component component = new DetailNewsItemComponent(newsItem, view);
        component.setWidth("90%");
        return component;
    }
}
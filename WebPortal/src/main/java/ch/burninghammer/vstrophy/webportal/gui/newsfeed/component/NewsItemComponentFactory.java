/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed.component;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.ui.Component;
import events.VSTrophyMVPView;
import javax.ejb.Stateless;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class NewsItemComponentFactory {

    public Component createCompactNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        Component component = new FeedNewsItemComponent(newsItem, view);
        component.setWidth("80%");
        return component;
    }

    public Component createFullNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        Component component = new DetailNewsItemComponent(newsItem, view);
        component.setWidth("80%");
        return component;
    }
}

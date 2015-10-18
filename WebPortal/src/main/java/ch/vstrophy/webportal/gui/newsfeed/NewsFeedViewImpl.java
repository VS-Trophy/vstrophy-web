/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.newsfeed;

import ch.vstrophy.entities.news.NewsItem;
import ch.vstrophy.webportal.gui.newsfeed.component.NewsItemComponentFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import ch.vstrophy.webportal.gui.events.VSTrophyMVPView;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsFeedViewImpl extends VSTrophyMVPView implements NewsFeedView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;

    @Inject
    @VerticalLayoutProperties(spacing = true, margin = true)
    private VerticalLayout mainLayout;

    @Inject
    private NewsItemComponentFactory newsItemComponentFactory;

    @PostConstruct
    private void initView() {
        this.setSizeFull();
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
    }

    @Override
    public void showNewsItems(List<NewsItem> newsItemList) {
        mainLayout.removeAllComponents();
        for (NewsItem newsItem : newsItemList) {
            Component comp = newsItemComponentFactory.createCompactNewsItemComponent(newsItem, this);
            mainLayout.addComponent(comp);
            mainLayout.setComponentAlignment(comp, Alignment.MIDDLE_CENTER);
        }
    }

    @Override
    public void showNewsItem(NewsItem newsItem) {
        mainLayout.removeAllComponents();
        Component comp = newsItemComponentFactory.createFullNewsItemComponent(newsItem, this);
        mainLayout.addComponent(comp);
        mainLayout.setComponentAlignment(comp, Alignment.MIDDLE_CENTER);
    }

}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsFeedViewImpl extends AbstractMVPView implements NewsFeedView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;

    @Inject
    @VerticalLayoutProperties(sizeUndefined = true, spacing = true, margin = true)
    private VerticalLayout mainLayout;

    @Inject
    private NewsItemComponentFactory newsItemComponentFactory;

    @PostConstruct
    private void initView() {
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        mainPanel.setHeight("400");
    }

    @Override
    public void showNewsItems(List<NewsItem> newsItemList) {
        mainLayout.removeAllComponents();
        for (NewsItem newsItem : newsItemList) {
            mainLayout.addComponent(newsItemComponentFactory.createNewsItemComponent(newsItem));
        }
    }

}

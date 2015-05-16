/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.LabelProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsFeedViewImpl extends AbstractMVPView implements NewsFeedView {

    @Inject
    @VerticalLayoutProperties(sizeFull = true)
    private VerticalLayout mainLayout;

    @Inject
    @LabelProperties(caption = "NEWS")
    private Label titleLabel;

    @PostConstruct
    private void initView() {
        mainLayout.addComponent(titleLabel);
        this.setCompositionRoot(mainLayout);
        JPAContainer<NewsItem> container = JPAContainerFactory.make(NewsItem.class, "ch.vstrophy_WebPortal_PU");
        Table newsTable = new Table("News", container);
        mainLayout.addComponent(newsTable);
    }

}

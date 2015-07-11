/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed.component;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import events.VSTrophyMVPView;
import java.text.DateFormat;
import org.vaadin.addon.cdimvp.ViewComponent;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public abstract class AbstractNewsItemComponent extends ViewComponent {

    private Panel mainPanel;
    private NewsItem newsItem;
    private VSTrophyMVPView view;
    protected VerticalLayout contentLayout;

    public AbstractNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        this.newsItem = newsItem;
        this.view = view;
        mainPanel = new Panel();
        contentLayout = new VerticalLayout();
        mainPanel.addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(contentLayout);
        this.mainPanel.setCaption(newsItem.getTitle());
    }

    protected void addDateLabel() {
        Label dateLabel = new Label();
        dateLabel.setSizeUndefined();
        contentLayout.addComponent(dateLabel);
        dateLabel.setValue(DateFormat.getInstance().format(newsItem.getPublicationDate()));
        contentLayout.setComponentAlignment(dateLabel, Alignment.TOP_RIGHT);
    }

    protected class FireEventClickListener implements Button.ClickListener {

        private final String cdiEvent;

        public FireEventClickListener(String event) {
            this.cdiEvent = event;
        }

        @Override
        public void buttonClick(Button.ClickEvent event) {
            view.fireCDIViewEvent(cdiEvent, newsItem);
        }

    }
}

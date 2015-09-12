/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed.component;

import ch.burninghammer.vstrophy.entities.news.NewsItem;
import ch.burninghammer.vstrophy.webportal.gui.events.VSTrophyMVPView;
import ch.burninghammer.vstrophy.webportal.gui.theme.VSTrophyTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.text.SimpleDateFormat;
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

    protected void addInfoLine() {
        HorizontalLayout infoLineLayout = new HorizontalLayout();
        infoLineLayout.setWidth("100%");
        Label dateLabel = new Label();
        dateLabel.setSizeUndefined();
        contentLayout.addComponent(infoLineLayout);
        dateLabel.setValue(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(newsItem.getPublicationDate()));
        dateLabel.addStyleName(VSTrophyTheme.LABEL_TINY);
        dateLabel.addStyleName(VSTrophyTheme.DARK);
        if (newsItem.getAuthor() != null) {
            Label authorLabel = new Label();
            authorLabel.setSizeUndefined();
            authorLabel.setValue("Verfasst von " + newsItem.getAuthor());
            authorLabel.addStyleName(VSTrophyTheme.LABEL_TINY);
            authorLabel.addStyleName(VSTrophyTheme.DARK);
            infoLineLayout.addComponent(authorLabel);
            infoLineLayout.setComponentAlignment(authorLabel, Alignment.TOP_LEFT);
        }
        infoLineLayout.addComponent(dateLabel);
        infoLineLayout.setComponentAlignment(dateLabel, Alignment.TOP_RIGHT);
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

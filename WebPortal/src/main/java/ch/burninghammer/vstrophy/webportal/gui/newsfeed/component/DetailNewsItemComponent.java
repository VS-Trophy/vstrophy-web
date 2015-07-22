/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed.component;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedCDIEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import ch.burninghammer.vstrophy.webportal.gui.events.VSTrophyMVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class DetailNewsItemComponent extends AbstractNewsItemComponent {

    private static int COMPACT_TEXT_SIZE_LIMIT = 2000;
    private Label textLabel;

    public DetailNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        super(newsItem, view);
        addDateLabel();

        textLabel = new Label();
        contentLayout.addComponent(textLabel);
        this.textLabel.setValue(newsItem.getText());
        textLabel.setContentMode(ContentMode.HTML);
        Button moreButton = new Button("Zurück");
        moreButton.addClickListener(new FireEventClickListener(NewsFeedCDIEvents.SHOW_NEWS_FEED));
        moreButton.setStyleName(ValoTheme.BUTTON_LINK);
        contentLayout.addComponent(moreButton);
        contentLayout.setComponentAlignment(moreButton, Alignment.BOTTOM_RIGHT);
    }

}

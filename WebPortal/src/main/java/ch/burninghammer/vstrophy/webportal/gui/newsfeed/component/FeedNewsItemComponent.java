/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed.component;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import ch.burninghammer.vstrophy.webportal.gui.events.VSTrophyMVPView;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedCDIEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class FeedNewsItemComponent extends AbstractNewsItemComponent {

    private static int COMPACT_TEXT_SIZE_LIMIT = 2000;
    private Label textLabel;

    public FeedNewsItemComponent(NewsItem newsItem, VSTrophyMVPView view) {
        super(newsItem, view);
        addInfoLine();
        textLabel = new Label();
        textLabel.setContentMode(ContentMode.HTML);
        contentLayout.addComponent(textLabel);
        contentLayout.setMargin(true);
        String text = newsItem.getText();
        text = compactify(text);
        this.textLabel.setValue(text);
        Button moreButton = new Button("ganzen Eintrag lesen");
        moreButton.addClickListener(new FireEventClickListener(NewsFeedCDIEvents.SHOW_NEWS_ITEM));
        moreButton.setStyleName(ValoTheme.BUTTON_LINK);
        contentLayout.addComponent(moreButton);
        contentLayout.setComponentAlignment(moreButton, Alignment.BOTTOM_RIGHT);
    }

    private String compactify(String text) {
        if (text == null || text.length() <= COMPACT_TEXT_SIZE_LIMIT) {
            return text;
        }
        return text.substring(0, COMPACT_TEXT_SIZE_LIMIT).concat("\n ...");
    }

}

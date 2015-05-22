/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.addon.cdimvp.ViewComponent;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsItemComponent extends ViewComponent {

    private Panel mainPanel;

    private Label textLabel;

    public NewsItemComponent(NewsItem newsItem) {
        mainPanel = new Panel();
        textLabel = new Label();
        textLabel.setContentMode(ContentMode.HTML);
        mainPanel.addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(textLabel);
        this.mainPanel.setCaption(newsItem.getTitle());
        this.textLabel.setValue(newsItem.getText());
    }
}

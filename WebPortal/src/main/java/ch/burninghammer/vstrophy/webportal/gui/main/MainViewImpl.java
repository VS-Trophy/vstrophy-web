/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import ch.burninghammer.vstrophy.webportal.entities.news.NewsItemPersistenceManager;
import com.vaadin.ui.Button;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class MainViewImpl extends AbstractMVPView implements MainView {

    @Inject
    private NewsItemPersistenceManager newsItemManager;

    @PostConstruct
    protected void initView() {
        setSizeFull();
        VerticalLayout mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Edit", null);
        menuBar.addItem("Login", null);
        mainLayout.addComponent(menuBar);
        mainLayout.addComponent(new Button("AddNewsItem", new addNewsItemClickListener()));

    }

    private class addNewsItemClickListener implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent event) {
            NewsItem item = new NewsItem();
            item.setText("Das ist der Text");
            item.setTitle("Das ist der Titel");
            newsItemManager.addNewsItem(item);
        }

    }
}

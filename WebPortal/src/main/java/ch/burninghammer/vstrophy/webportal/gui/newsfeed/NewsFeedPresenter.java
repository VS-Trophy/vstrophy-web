/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newsfeed;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItemEntityManager;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(NewsFeedView.class)
public class NewsFeedPresenter extends AbstractMVPPresenter<NewsFeedView> {

    @Inject
    private NewsItemEntityManager newsItemEntityManager;

    @Override
    public void viewEntered() {
        view.showNewsItems(newsItemEntityManager.getAllNewsItems());
    }

}

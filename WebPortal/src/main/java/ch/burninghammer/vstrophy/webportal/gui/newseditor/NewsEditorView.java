/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface NewsEditorView extends MVPView {

    public void showNewsItemDetails(NewsItem newsItem);

    public void showNewsItemlist(List<NewsItem> newsItemList);
}

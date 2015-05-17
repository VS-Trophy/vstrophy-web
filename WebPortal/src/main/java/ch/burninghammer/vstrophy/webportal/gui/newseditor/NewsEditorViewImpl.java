/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.RichTextAreaProperties;
import org.vaadin.addon.cdiproperties.annotation.TableProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsEditorViewImpl extends AbstractMVPView implements NewsEditorView {

    @Inject
    @HorizontalLayoutProperties(sizeFull = true)
    private HorizontalLayout mainLayout;

    @Inject
    @TableProperties(immediate = true)
    private Table newsTable;

    @Inject
    @RichTextAreaProperties()
    private RichTextArea editor;

    @PostConstruct
    private void initView() {
        this.setCompositionRoot(mainLayout);
        newsTable.addValueChangeListener(new NewsListValueChangedListener());
        newsTable.setSelectable(true);
        mainLayout.addComponent(newsTable);
        mainLayout.addComponent(editor);
    }

    @Override
    public void showSelectedNewsItemDetails() {
        Object value = newsTable.getValue();
        if (value != null) {
            Item selectedNewsItem = newsTable.getItem(value);
            Property property = selectedNewsItem.getItemProperty("text");
            editor.setPropertyDataSource(property);
        }
    }

    @Override
    public void showNewsItemlist(List<NewsItem> newsItemlist) {

    }

    private class NewsListValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            fireViewEvent(NewsEditorCDIEvents.newsItemSelected, null);
        }

    }
}

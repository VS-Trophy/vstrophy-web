/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.TableProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsEditorViewImpl extends AbstractMVPView implements NewsEditorView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;

    @Inject
    @HorizontalLayoutProperties(sizeFull = true, margin = true)
    private HorizontalLayout mainLayout;

    @Inject
    @TableProperties(immediate = true, sizeUndefined = true)
    private Table newsTable;

    @Inject
    @VerticalLayoutProperties(sizeUndefined = true, height = "100%")
    private VerticalLayout tableLayout;

    @Inject
    @ButtonProperties(caption = "Neu")
    private Button newNewsItemButton;

    @Inject
    private NewsItemForm form;

    @PostConstruct
    private void initView() {
        setSizeFull();
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        newsTable.addValueChangeListener(new NewsListValueChangedListener());
        newsTable.setSelectable(true);
        tableLayout.addComponent(newsTable);
        tableLayout.addComponent(newNewsItemButton);
        newNewsItemButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                event.getButton().setEnabled(false);
                fireViewEvent(NewsEditorCDIEvents.NEWS_ITEM_ADD, mainLayout);
            }
        });
        mainLayout.addComponent(tableLayout);

    }

    @Override
    public void showNewsItemDetails(NewsItem newsItem) {
        mainLayout.addComponent(form);
        mainLayout.setExpandRatio(form, 1.0f);
        form.bindNewsItem(newsItem);
    }

    @Override
    public void showNewsItemlist(List<NewsItem> newsItemlist) {
        BeanContainer<Integer, NewsItem> newsItemBeanContainer = new BeanContainer<>(NewsItem.class);
        newsItemBeanContainer.setBeanIdProperty("id");
        newsItemBeanContainer.addAll(newsItemlist);
        newsTable.setContainerDataSource(newsItemBeanContainer);
        newsTable.setVisibleColumns("title", "publicationDate");
        newNewsItemButton.setEnabled(true);
        mainLayout.removeComponent(form);
    }

    private class NewsListValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty().getValue() != null) {
                BeanItem<NewsItem> item = (BeanItem) newsTable.getItem(event.getProperty().getValue());
                fireViewEvent(NewsEditorCDIEvents.NEWS_ITEM_SELECTED, item.getBean());
            }
        }
    }
}

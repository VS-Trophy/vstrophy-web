/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.HorizontalSplitPanelProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.TableProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsEditorViewImpl extends AbstractMVPView implements NewsEditorView {

    @Inject
    @PanelProperties(sizeFull = true, heightUnits = Unit.PERCENTAGE, heightValue = 100)
    private Panel mainPanel;

    @Inject
    @HorizontalSplitPanelProperties(sizeFull = true, locked = true)
    private HorizontalSplitPanel mainLayout;

    @Inject
    @TableProperties(immediate = true, sizeFull = true)
    private Table newsTable;

    @Inject
    private NewsItemForm form;

    @PostConstruct
    private void initView() {
        setSizeFull();
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        newsTable.addValueChangeListener(new NewsListValueChangedListener());
        newsTable.setSelectable(true);
        mainLayout.setFirstComponent(newsTable);
        mainLayout.setSecondComponent(form);
    }

    @Override
    public void showSelectedNewsItemDetails() {
        BeanItem<NewsItem> item = (BeanItem) newsTable.getItem(newsTable.getValue());
        form.bindNewsItem(item.getBean());
    }

    @Override
    public void showNewsItemlist(List<NewsItem> newsItemlist) {
        BeanContainer<Integer, NewsItem> newsItemBeanContainer = new BeanContainer<>(NewsItem.class);
        newsItemBeanContainer.setBeanIdProperty("id");
        newsItemBeanContainer.addAll(newsItemlist);
        newsTable.setContainerDataSource(newsItemBeanContainer);
        newsTable.setVisibleColumns("title", "text", "publicationDate");
    }

    private class NewsListValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty().getValue() != null) {
                fireViewEvent(NewsEditorCDIEvents.newsItemSelected, event.getProperty().getValue());
            }
        }
    }
}

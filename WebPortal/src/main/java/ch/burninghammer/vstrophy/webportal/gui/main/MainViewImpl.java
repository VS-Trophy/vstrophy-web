/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.gui.newseditor.NewsEditorViewImpl;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedViewImpl;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdimvp.MVPView;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class MainViewImpl extends AbstractMVPView implements MainView {

    @Inject
    private Instance<NewsFeedViewImpl> newsFeedView;

    @Inject
    private Instance<NewsEditorViewImpl> newsEditorView;

    @Inject
    @HorizontalLayoutProperties(sizeFull = true)
    private HorizontalLayout mainLayout;

    @Inject
    @VerticalLayoutProperties(sizeFull = true)
    private VerticalLayout content;

    @PostConstruct
    protected void initView() {
        setSizeFull();
        setCompositionRoot(mainLayout);
        mainLayout.addComponent(createMainMenu());
        mainLayout.addComponent(content);
        mainLayout.setExpandRatio(content, 0.9f);
    }

    private Component createMainMenu() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        //layout.setWidth("10%");
        Button newsFeedButton = new Button();
        newsFeedButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_NEWSFEED));
        newsFeedButton.setIcon(FontAwesome.FILE_TEXT_O);
        newsFeedButton.addStyleName(ValoTheme.BUTTON_HUGE);

        Button newsEditorButton = new Button();
        newsEditorButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_NEWSEDITOR));
        newsEditorButton.setIcon(FontAwesome.PENCIL);
        newsEditorButton.addStyleName(ValoTheme.BUTTON_HUGE);

        layout.addComponent(newsFeedButton);
        layout.addComponent(newsEditorButton);
        return layout;
    }

    @Override
    public void setView(MVPView mvpView) {
        content.removeAllComponents();
        content.addComponent((Component) mvpView);
    }

    private class CDIEventClickListener implements Button.ClickListener {

        private final String cdiEvent;

        public CDIEventClickListener(String cdiEvent) {
            this.cdiEvent = cdiEvent;
        }

        @Override
        public void buttonClick(Button.ClickEvent event) {
            fireViewEvent(cdiEvent, null);
        }

    }
}

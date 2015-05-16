/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.gui.newseditor.NewsEditorViewImpl;
import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedViewImpl;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdimvp.MVPView;
import org.vaadin.addon.cdiproperties.annotation.MenuBarProperties;
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
    @VerticalLayoutProperties(sizeFull = true)
    private VerticalLayout mainLayout;

    @Inject
    @MenuBarProperties()
    private MenuBar mainMenu;

    @Inject
    @VerticalLayoutProperties(sizeFull = true)
    private VerticalLayout content;

    @Inject
    private Instance<MVPView> views;

    @PostConstruct
    protected void initView() {
        setSizeFull();
        setCompositionRoot(mainLayout);
        mainMenu.addItem("Newsfeed", new CDIEventCommand(MainMenuCDIEvents.SHOW_NEWSFEED));
        mainMenu.addItem("News Editor", new CDIEventCommand(MainMenuCDIEvents.SHOW_NEWSEDITOR));
        mainLayout.addComponent(mainMenu);
        mainLayout.addComponent(content);
        mainLayout.setExpandRatio(content, 1.0f);

    }

    @Override
    public void setView(final Class<? extends MVPView> viewClass) {
        MVPView view = views.select(viewClass).get();
        content.removeAllComponents();
        content.addComponent((Component) view);
    }

    private class CDIEventCommand implements MenuBar.Command {

        private final String cdiEvent;

        public CDIEventCommand(String cdiEvent) {
            this.cdiEvent = cdiEvent;
        }

        @Override
        public void menuSelected(MenuBar.MenuItem selectedItem) {
            fireViewEvent(cdiEvent, null);
        }

    }
}

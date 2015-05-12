/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import ch.burninghammer.vstrophy.webportal.gui.newsfeed.NewsFeedViewImpl;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class MainViewImpl extends AbstractMVPView implements MainView {

    @Inject
    private Instance<NewsFeedViewImpl> newsFeedView;

    @PostConstruct
    protected void initView() {
        setSizeFull();
        VerticalLayout mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Edit", null);
        menuBar.addItem("Login", null);
        mainLayout.addComponent(menuBar);
        mainLayout.addComponent(newsFeedView.get());
    }
}

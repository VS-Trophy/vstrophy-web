/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.main;

import ch.vstrophy.webportal.gui.main.login.LoginButtonComponent;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdimvp.MVPView;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class MainViewImpl extends AbstractMVPView implements MainView {

    private static final String MENU_WIDTH = "120px";

    @Inject
    @VerticalLayoutProperties(sizeFull = true)
    private VerticalLayout mainLayout;

    @Inject
    @HorizontalLayoutProperties(sizeFull = true)
    private HorizontalLayout contentLayout;

    @Inject
    @VerticalLayoutProperties(sizeFull = true)
    private VerticalLayout content;

    @Inject
    @ButtonProperties(caption = "news")
    private Button newsFeedButton;
    @Inject
    @ButtonProperties(caption = "Geschichte")
    private Button historyButton;
    @Inject
    @ButtonProperties(caption = "News Editor")
    private Button newsEditorButton;
    @Inject
    @ButtonProperties(caption = "User")
    private Button userAdministrationButton;
    @Inject
    @ButtonProperties(caption = "Teams")
    private Button teamsButton;
    @Inject
    @ButtonProperties(caption = "Team Editor")
    private Button teamEditorButton;
    @Inject
    @ButtonProperties(caption = "Division Editor")
    private Button divisionEditorButton;
    @Inject
    @ButtonProperties(caption = "Resultate")
    private Button resultButton;

    private List<Button> publicButtons;
    private List<Button> allButtons;

    @Inject
    @VerticalLayoutProperties()
    private VerticalLayout mainMenuLayout;
    @Inject
    private LoginButtonComponent loginButton;

    @PostConstruct
    protected void initView() {
        setSizeFull();
        setCompositionRoot(mainLayout);
        mainLayout.addComponent(createTitleBar());
        mainLayout.addComponent(contentLayout);
        mainLayout.setExpandRatio(contentLayout, 1.0f);
        contentLayout.addComponent(createMainMenu());
        contentLayout.addComponent(content);
        contentLayout.setExpandRatio(content, 0.9f);

    }

    private Component createMainMenu() {
        mainMenuLayout.setMargin(new MarginInfo(true, false, false, false));
        mainMenuLayout.setSizeUndefined();
        mainMenuLayout.setWidth(MENU_WIDTH);

        newsFeedButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_NEWSFEED));
        newsFeedButton.setIcon(FontAwesome.FILE_TEXT_O);
        newsFeedButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        newsFeedButton.setCaption("News");
        newsFeedButton.setSizeFull();

        newsEditorButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_NEWSEDITOR));
        newsEditorButton.setIcon(FontAwesome.PENCIL);
        newsEditorButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        newsEditorButton.setSizeFull();

        userAdministrationButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_USER_ADMINISTRATION));
        userAdministrationButton.setIcon(FontAwesome.USERS);
        userAdministrationButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        userAdministrationButton.setSizeFull();

        resultButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_RESULTS));
        resultButton.setIcon(FontAwesome.CALENDAR);
        resultButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        resultButton.setSizeFull();

        teamsButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_TEAMS));
        teamsButton.setIcon(FontAwesome.SITEMAP);
        teamsButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        teamsButton.setSizeFull();

        historyButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_HISTORY));
        historyButton.setIcon(FontAwesome.INFO_CIRCLE);
        historyButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        historyButton.setSizeFull();

        teamEditorButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_TEAM_EDITOR));
        teamEditorButton.setIcon(FontAwesome.PENCIL);
        teamEditorButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        teamEditorButton.setSizeFull();

        divisionEditorButton.addClickListener(new CDIEventClickListener(MainMenuCDIEvents.SHOW_DIVISION_EDITOR));
        divisionEditorButton.setIcon(FontAwesome.PENCIL);
        divisionEditorButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        divisionEditorButton.setSizeFull();

        publicButtons = new ArrayList<>();
        allButtons = new ArrayList<>();

        allButtons.add(newsFeedButton);
        allButtons.add(resultButton);
        allButtons.add(teamsButton);
        allButtons.add(historyButton);
        allButtons.add(newsEditorButton);
        allButtons.add(teamEditorButton);
        allButtons.add(divisionEditorButton);
        allButtons.add(userAdministrationButton);

        publicButtons.add(newsFeedButton);
        publicButtons.add(resultButton);
        publicButtons.add(teamsButton);
        publicButtons.add(historyButton);

        return mainMenuLayout;
    }

    private Component createTitleBar() {
        HorizontalLayout titleBarLayout = new HorizontalLayout();
        titleBarLayout.setSpacing(true);
        titleBarLayout.setMargin(new MarginInfo(false, true, false, false));
        titleBarLayout.setSizeUndefined();
        titleBarLayout.setWidth("100%");
        VerticalLayout logoLayout = new VerticalLayout();

        Image logo = new Image(null, new ClassResource("/pics/Logo.png"));
        logoLayout.addComponent(logo);
        logoLayout.setWidth(MENU_WIDTH);
        logoLayout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        Label title = new Label("VS-Trophy Webportal");
        title.addStyleName("v-label-title");
        title.setSizeUndefined();
        titleBarLayout.addComponent(logoLayout);
        titleBarLayout.addComponent(title);
        titleBarLayout.addComponent(loginButton);
        titleBarLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);
        titleBarLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        return titleBarLayout;
    }

    @Override
    public void setView(MVPView mvpView) {
        content.removeAllComponents();
        content.addComponent((Component) mvpView);
    }

    @Override
    public void showAllButtons() {
        mainMenuLayout.removeAllComponents();
        for (Button button : allButtons) {
            mainMenuLayout.addComponent(button);
        }

    }

    @Override
    public void showPublicButtons() {
        mainMenuLayout.removeAllComponents();
        for (Button button : publicButtons) {
            mainMenuLayout.addComponent(button);
        }

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

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.useradministration;

import ch.vstrophy.entities.user.User;
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
public class UserAdministrationViewImpl extends AbstractMVPView implements UserAdministrationView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;

    @Inject
    @HorizontalLayoutProperties(sizeFull = true, margin = true)
    private HorizontalLayout mainLayout;

    @Inject
    @TableProperties(immediate = true, sizeUndefined = true, pageLength = 10)
    private Table userTable;

    @Inject
    @VerticalLayoutProperties(sizeUndefined = true, height = "100%")
    private VerticalLayout tableLayout;

    @Inject
    @ButtonProperties(caption = "Neu")
    private Button newUserButton;

    @Inject
    private UserForm form;

    @PostConstruct
    private void initView() {
        setSizeFull();
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        userTable.addValueChangeListener(new UserListValueChangedListener());
        userTable.setSelectable(true);
        tableLayout.addComponent(userTable);
        tableLayout.addComponent(newUserButton);
        newUserButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                event.getButton().setEnabled(false);
                fireViewEvent(UserAdministrationCDIEvents.USER_ADD, mainLayout);
            }
        });
        mainLayout.addComponent(tableLayout);

    }

    @Override
    public void showUserDetails(User user) {
        mainLayout.addComponent(form);
        mainLayout.setExpandRatio(form, 1.0f);
        form.bindUser(user);
    }

    @Override
    public void showUserList(List<User> userList) {
        BeanContainer<Integer, User> userBeanContainer = new BeanContainer<>(User.class);
        userBeanContainer.setBeanIdProperty("id");
        userBeanContainer.addAll(userList);
        userTable.setContainerDataSource(userBeanContainer);
        userTable.setVisibleColumns("name", "admin");
        newUserButton.setEnabled(true);
        mainLayout.removeComponent(form);
    }

    private class UserListValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty().getValue() != null) {
                BeanItem<User> item = (BeanItem) userTable.getItem(event.getProperty().getValue());
                fireViewEvent(UserAdministrationCDIEvents.USER_SELECTED, item.getBean());
            }
        }
    }

}

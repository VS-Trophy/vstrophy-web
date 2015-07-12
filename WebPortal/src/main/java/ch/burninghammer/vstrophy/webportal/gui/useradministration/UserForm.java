/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.useradministration;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.CheckBoxProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.PasswordFieldProperties;
import org.vaadin.addon.cdiproperties.annotation.TextFieldProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class UserForm extends ViewComponent {

    @PropertyId("name")
    @Inject
    @TextFieldProperties(immediate = true, caption = "Titel")
    private TextField nameTextField;

    @Inject
    @PasswordFieldProperties(caption = "Passwort", immediate = true)
    private PasswordField passwordField;

    @Inject
    @PropertyId("admin")
    @CheckBoxProperties(caption = "Administrator", immediate = true)
    private CheckBox adminCheckbox;

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout formLayout;

    @Inject
    @ButtonProperties(enabled = false, caption = "Speichern")
    private Button button;

    private FieldGroup fieldGroup;

    private User user;

    @PostConstruct
    public void init() {
        formLayout.addComponent(nameTextField);
        formLayout.addComponent(passwordField);
        formLayout.addComponent(adminCheckbox);
        setCompositionRoot(formLayout);
        formLayout.addComponent(button);
        button.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    fieldGroup.clear();
                } catch (FieldGroup.CommitException ex) {
                    Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                fireViewEvent(UserAdministrationCDIEvents.USER_CHANGED, user, passwordField.getValue());
            }
        });
    }

    public void bindUser(User user) {
        fieldGroup = new FieldGroup(new BeanItem<>(user));
        fieldGroup.bindMemberFields(this);
        this.user = user;
        button.setEnabled(true);
    }

}

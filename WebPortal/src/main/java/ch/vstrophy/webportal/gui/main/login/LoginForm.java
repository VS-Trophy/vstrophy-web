/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.main.login;

import ch.vstrophy.webportal.gui.main.MainMenuCDIEvents;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.PasswordFieldProperties;
import org.vaadin.addon.cdiproperties.annotation.TextFieldProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class LoginForm extends ViewComponent {

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout formLayout;

    @Inject
    @TextFieldProperties(caption = "Benutzername")
    private TextField userTextField;

    @Inject
    @PasswordFieldProperties(caption = "Passwort")
    private PasswordField passwordField;

    @Inject
    @ButtonProperties(caption = "Einloggen")
    private Button button;

    @PostConstruct
    private void init() {
        this.setCompositionRoot(formLayout);
        formLayout.addComponent(userTextField);
        formLayout.addComponent(passwordField);
        formLayout.addComponent(button);
        button.addClickListener(new LoginClickListener());
        button.setClickShortcut(KeyCode.ENTER);

    }

    private class LoginClickListener implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent event) {
            fireViewEvent(MainMenuCDIEvents.LOGIN_CLICKED, userTextField.getValue(), passwordField.getValue());
        }
    }

}

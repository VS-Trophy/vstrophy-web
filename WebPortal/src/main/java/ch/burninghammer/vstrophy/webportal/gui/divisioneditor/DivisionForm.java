/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.divisioneditor;

import ch.burninghammer.vstrophy.entities.divisions.Division;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Button;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.ColorPickerProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.TextFieldProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class DivisionForm extends ViewComponent {

    @Inject
    @TextFieldProperties(immediate = true, caption = "Name")
    private TextField nameTextField;

    @Inject
    @ColorPickerProperties(caption = "Farbe", immediate = true)
    private ColorPicker colorPicker;

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout mainFormLayout;

    @Inject
    @ButtonProperties(enabled = false, caption = "Speichern")
    private Button saveButton;

    private Division division;

    private static enum ImageType {

        LOGO, UNIFORM
    };

    @PostConstruct
    public void init() {

        mainFormLayout.addComponent(nameTextField);
        mainFormLayout.addComponent(colorPicker);
        mainFormLayout.addComponent(saveButton);
        saveButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                save();
            }
        });

        setCompositionRoot(mainFormLayout);

    }

    private void save() {
        this.division.setName(nameTextField.getValue());
        this.division.setColor(colorPicker.getColor().getRGB());
        fireViewEvent(DivisionEditorCDIEvents.DIVISION_CHANGED, division);
    }

    public void bindDivision(Division division) {
        this.division = division;
        this.nameTextField.setValue(division.getName());
        this.colorPicker.setColor(new Color(division.getColor()));
        saveButton.setEnabled(true);
    }

}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teameditor;

import ch.burninghammer.vstrophy.webportal.entities.teams.Team;
import ch.burninghammer.vstrophy.webportal.entities.teams.TeamOfficial;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.DateFieldProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.TableProperties;
import org.vaadin.addon.cdiproperties.annotation.TextFieldProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamForm extends ViewComponent {

    @PropertyId("name")
    @Inject
    @TextFieldProperties(immediate = true, caption = "Name")
    private TextField nameTextField;

    @PropertyId("city")
    @Inject
    @TextFieldProperties(immediate = true, caption = "Stadt")
    private TextField cityTextField;

    @PropertyId("stadium")
    @Inject
    @TextFieldProperties(immediate = true, caption = "Stadium")
    private TextField stadiumTextField;

    @Inject
    @PropertyId("foundedIn")
    @DateFieldProperties(immediate = true, caption = "Gegründet")
    private DateField foundedInDateField;

    @Inject
    @PropertyId("joinedIn")
    @DateFieldProperties(immediate = true, caption = "Mitglied seit")
    private DateField joinedInDateField;

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout formLayout;

    @Inject
    @ButtonProperties(enabled = false, caption = "Speichern")
    private Button saveButton;

    @Inject
    @TableProperties(editable = true)
    private Table officialsTable;

    private FieldGroup fieldGroup;

    private Team team;

    @PostConstruct
    public void init() {
        formLayout.addComponent(nameTextField);
        formLayout.addComponent(cityTextField);
        formLayout.addComponent(stadiumTextField);
        formLayout.addComponent(foundedInDateField);
        formLayout.addComponent(joinedInDateField);
        formLayout.addComponent(officialsTable);

        setCompositionRoot(formLayout);
        formLayout.addComponent(saveButton);
        saveButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    fieldGroup.clear();
                } catch (FieldGroup.CommitException ex) {
                    Logger.getLogger(TeamForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                fireViewEvent(TeamEditorCDIEvents.TEAM_CHANGED, team);
            }
        });
    }

    public void bindTeam(Team team) {
        fieldGroup = new FieldGroup(new BeanItem<>(team));
        fieldGroup.bindMemberFields(this);
        this.team = team;
        saveButton.setEnabled(true);

        BeanItemContainer officialsContainer = new BeanItemContainer(TeamOfficial.class);
        if (team != null) {

        }
    }

}

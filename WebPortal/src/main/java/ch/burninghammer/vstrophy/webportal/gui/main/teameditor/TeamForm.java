/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teameditor;

import ch.burninghammer.vstrophy.webportal.entities.teams.Team;
import ch.burninghammer.vstrophy.webportal.entities.teams.TeamOfficial;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
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
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
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
    @TableProperties(selectable = true, editable = true, pageLength = 5)
    private Table officialsTable;

    @Inject
    @HorizontalLayoutProperties
    private HorizontalLayout tableButtonLayout;

    @Inject
    @ButtonProperties(caption = "+")
    private Button addOfficialButton;

    @Inject
    @ButtonProperties(caption = "-", enabled = false)
    private Button removeOfficialButton;

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout formLayout;

    @Inject
    @ButtonProperties(enabled = false, caption = "Speichern")
    private Button saveButton;

    private FieldGroup fieldGroup;

    private BeanItemContainer officialsContainer;

    private Team team;

    @PostConstruct
    public void init() {
        formLayout.addComponent(nameTextField);
        formLayout.addComponent(cityTextField);
        formLayout.addComponent(stadiumTextField);
        formLayout.addComponent(foundedInDateField);
        formLayout.addComponent(joinedInDateField);
        formLayout.addComponent(officialsTable);
        formLayout.addComponent(tableButtonLayout);
        tableButtonLayout.addComponent(addOfficialButton);
        tableButtonLayout.addComponent(removeOfficialButton);
        formLayout.addComponent(saveButton);

        saveButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    team.setOfficials(officialsContainer.getItemIds());
                    fieldGroup.commit();
                    fieldGroup.clear();
                } catch (FieldGroup.CommitException ex) {
                    Logger.getLogger(TeamForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                fireViewEvent(TeamEditorCDIEvents.TEAM_CHANGED, team);
            }
        });

        addOfficialButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                officialsContainer.addBean(new TeamOfficial());
            }
        });

        removeOfficialButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (officialsTable.getValue() != null) {
                    officialsTable.removeItem(officialsTable.getValue());
                }
            }
        });

        officialsTable.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (officialsTable.getValue() == null) {
                    removeOfficialButton.setEnabled(false);
                } else {
                    removeOfficialButton.setEnabled(true);
                }
            }
        });
        setCompositionRoot(formLayout);
    }

    public void bindTeam(Team team) {
        fieldGroup = new FieldGroup(new BeanItem<>(team));
        fieldGroup.bindMemberFields(this);
        this.team = team;
        officialsContainer = new BeanItemContainer(TeamOfficial.class);
        officialsContainer.addAll(team.getOfficials());
        officialsTable.setContainerDataSource(officialsContainer);
        saveButton.setEnabled(true);
    }

}

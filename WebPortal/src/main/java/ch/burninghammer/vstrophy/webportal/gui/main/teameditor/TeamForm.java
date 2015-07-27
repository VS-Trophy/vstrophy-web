/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teameditor;

import ch.burninghammer.vstrophy.webportal.entities.teams.Team;
import ch.burninghammer.vstrophy.webportal.entities.teams.TeamOfficial;
import ch.burninghammer.vstrophy.webportal.gui.main.teams.component.ByteArrayStreamResource;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.DateFieldProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.ImageProperties;
import org.vaadin.addon.cdiproperties.annotation.TableProperties;
import org.vaadin.addon.cdiproperties.annotation.TextFieldProperties;
import org.vaadin.addon.cdiproperties.annotation.UploadProperties;

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
    @PropertyId("colors")
    @TextFieldProperties(immediate = true, caption = "Farben")
    private TextField colorsTextField;

    @Inject
    @PropertyId("fans")
    @TextFieldProperties(immediate = true, caption = "Fans")
    private TextField fansTextField;

    @Inject
    @TableProperties(selectable = true, editable = true, pageLength = 5, immediate = true)
    private Table officialsTable;

    @Inject
    @UploadProperties(caption = "Uniform", immediate = true, styleName = "v-upload-immediate")
    private Upload uniformUpload;

    @Inject
    @ImageProperties(alternateText = "Uniform", immediate = true)
    private Image uniformImage;

    @Inject
    @UploadProperties(caption = "Logo", immediate = true, styleName = "v-upload-immediate")
    private Upload logoUpload;

    @Inject
    @ImageProperties(alternateText = "Logo", immediate = true)
    private Image logoImage;

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
    @HorizontalLayoutProperties(width = "100%")
    private HorizontalLayout mainLayout;

    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout leftFormLayout;
    @Inject
    @FormLayoutProperties(sizeFull = true)
    private FormLayout rightFormLayout;

    @Inject
    @ButtonProperties(enabled = false, caption = "Speichern")
    private Button saveButton;

    private FieldGroup fieldGroup;

    private BeanItemContainer officialsContainer;

    private Team team;

    private static enum ImageType {

        LOGO, UNIFORM
    };

    @PostConstruct
    public void init() {
        mainLayout.addComponent(leftFormLayout);
        mainLayout.addComponent(rightFormLayout);

        leftFormLayout.addComponent(nameTextField);
        leftFormLayout.addComponent(cityTextField);
        leftFormLayout.addComponent(stadiumTextField);
        leftFormLayout.addComponent(foundedInDateField);
        leftFormLayout.addComponent(joinedInDateField);
        leftFormLayout.addComponent(colorsTextField);
        leftFormLayout.addComponent(fansTextField);
        leftFormLayout.addComponent(officialsTable);
        leftFormLayout.addComponent(tableButtonLayout);
        tableButtonLayout.addComponent(addOfficialButton);
        tableButtonLayout.addComponent(removeOfficialButton);
        leftFormLayout.addComponent(saveButton);

        saveButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                save();
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

        rightFormLayout.addComponent(logoImage);
        rightFormLayout.addComponent(logoUpload);
        ImageUploadReceiver logoReceiver = new ImageUploadReceiver(ImageType.LOGO);
        logoUpload.setReceiver(logoReceiver);
        logoUpload.addSucceededListener(logoReceiver);
        rightFormLayout.addComponent(uniformImage);
        rightFormLayout.addComponent(uniformUpload);
        ImageUploadReceiver uniformReceiver = new ImageUploadReceiver(ImageType.UNIFORM);
        uniformUpload.setReceiver(uniformReceiver);
        uniformUpload.addSucceededListener(uniformReceiver);

        setCompositionRoot(mainLayout);

    }

    private void save() {
        try {
            team.setOfficials(officialsContainer.getItemIds());
            fieldGroup.commit();
        } catch (FieldGroup.CommitException ex) {
            Logger.getLogger(TeamForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireViewEvent(TeamEditorCDIEvents.TEAM_CHANGED, team);
    }

    public void bindTeam(Team team) {
        fieldGroup = new FieldGroup(new BeanItem<>(team));
        fieldGroup.bindMemberFields(this);
        this.team = team;
        officialsContainer = new BeanItemContainer(TeamOfficial.class);
        officialsContainer.addAll(team.getOfficials());
        officialsTable.setContainerDataSource(officialsContainer);
        refreshImages();
        saveButton.setEnabled(true);
    }

    private StreamResource createStreamResource(final byte[] data, String fileName) {
        return new ByteArrayStreamResource(data, fileName + data.length);
    }

    private void refreshImages() {

        logoImage.setSource(createStreamResource(team.getLogo(), team.getName() + "logo" + System.currentTimeMillis()));
        logoImage.markAsDirty();
        uniformImage.setSource(createStreamResource(team.getUniformPicture(), team.getName() + "uniform" + System.currentTimeMillis()));
        uniformImage.markAsDirty();
    }

    private class ImageUploadReceiver implements Upload.Receiver, Upload.SucceededListener {

        private final ByteArrayOutputStream stream;
        private final ImageType imageType;

        public ImageUploadReceiver(ImageType imageType) {
            this.imageType = imageType;
            stream = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream receiveUpload(String filename, String mimeType) {
            return stream;
        }

        @Override
        public void uploadSucceeded(Upload.SucceededEvent event) {
            switch (imageType) {
                case LOGO:
                    team.setLogo(stream.toByteArray());
                    break;
                case UNIFORM:
                    team.setUniformPicture(stream.toByteArray());
                    break;
            }
            refreshImages();
        }

    }

}

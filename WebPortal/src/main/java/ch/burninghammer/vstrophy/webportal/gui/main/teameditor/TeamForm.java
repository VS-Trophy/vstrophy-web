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
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    @TableProperties(selectable = true, editable = true, pageLength = 5)
    private Table officialsTable;

    @Inject
    @UploadProperties(caption = "Uniform", immediate = true, styleName = "v-upload-immediate")
    private Upload uniformUpload;

    @Inject
    @ImageProperties(alternateText = "Uniform")
    private Image uniformImage;

    @Inject
    @UploadProperties(caption = "Logo", immediate = true, styleName = "v-upload-immediate")
    private Upload logoUpload;

    @Inject
    @ImageProperties(alternateText = "Logo")
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
                try {
                    team.setOfficials(officialsContainer.getItemIds());
                    team.setLogo(extractDataFromImage(logoImage));
                    team.setUniformPicture(extractDataFromImage(uniformImage));
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

        rightFormLayout.addComponent(logoImage);
        rightFormLayout.addComponent(logoUpload);
        ImageUploadReceiver logoReceiver = new ImageUploadReceiver(logoImage);
        logoUpload.setReceiver(logoReceiver);
        logoUpload.addSucceededListener(logoReceiver);
        rightFormLayout.addComponent(uniformImage);
        rightFormLayout.addComponent(uniformUpload);
        ImageUploadReceiver uniformReceiver = new ImageUploadReceiver(uniformImage);
        uniformUpload.setReceiver(uniformReceiver);
        uniformUpload.addSucceededListener(uniformReceiver);

        setCompositionRoot(mainLayout);

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

    private byte[] extractDataFromImage(final Image image) {
        Object imageData = image.getData();
        if (imageData != null) {
            byte[] bytes;
            try {
                bytes = ((ByteArrayStreamSource) imageData).getData();
                return bytes;
            } catch (ClassCastException ex) {
                return null;
            }
        }
        return null;
    }

    private class ImageUploadReceiver implements Upload.Receiver, Upload.SucceededListener {

        private Image image;

        private ByteArrayOutputStream stream;

        public ImageUploadReceiver(Image image) {
            this.image = image;
            stream = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream receiveUpload(String filename, String mimeType) {
            return stream;
        }

        @Override
        public void uploadSucceeded(Upload.SucceededEvent event) {
            ByteArrayStreamSource source = new ByteArrayStreamSource(stream.toByteArray());
            image.setSource(new StreamResource(source, image.getAlternateText()));
            image.setData(source);
        }

    }

    private class ByteArrayStreamSource implements StreamSource {

        public byte[] data;

        public ByteArrayStreamSource(final byte[] data) {
            this.data = data;
        }

        public byte[] getData() {
            return data;
        }

        @Override
        public InputStream getStream() {
            return new ByteArrayInputStream(data);
        }

    }

}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.history;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.LabelProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.RichTextAreaProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class HistoryViewImpl extends AbstractMVPView implements HistoryView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;

    @Inject
    @VerticalLayoutProperties(sizeFull = true, margin = true)
    private VerticalLayout mainLayout;

    @Inject
    @LabelProperties(sizeFull = true, readOnly = true)
    private Label historyLabel;
    @Inject
    @RichTextAreaProperties(sizeFull = true)
    private RichTextArea historyTextField;

    @Inject
    @ButtonProperties
    private Button editButton;

    private boolean isEditMode;

    @PostConstruct
    private void init() {
        mainPanel.setContent(mainLayout);
        setCompositionRoot(mainPanel);
        mainLayout.addComponent(historyLabel);
        editButton.setIcon(FontAwesome.PENCIL);
        isEditMode = false;
        editButton.addClickListener(new EditButtonClickListener());
    }

    @Override
    public void showHistory(String content, boolean isEditable) {
        historyLabel.setValue(content);
        if (isEditable) {
            mainLayout.addComponent(editButton);
        } else {
            mainLayout.removeComponent(editButton);
        }
    }

    private class EditButtonClickListener implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent event) {
            if (isEditMode) {
                isEditMode = false;

                historyLabel.setValue(historyTextField.getValue());
                mainLayout.removeAllComponents();
                mainLayout.addComponent(historyLabel);
                mainLayout.addComponent(editButton);
                fireViewEvent(HistoryViewCDIEvents.HISTORY_VIEW_PERSISTENT_TEXT_CHANGED, historyLabel.getValue());
            } else {
                isEditMode = true;
                historyTextField.setValue(historyLabel.getValue());
                mainLayout.removeAllComponents();
                mainLayout.removeComponent(historyLabel);
                mainLayout.addComponent(historyTextField);
                mainLayout.addComponent(editButton);
            }
        }

    }
}

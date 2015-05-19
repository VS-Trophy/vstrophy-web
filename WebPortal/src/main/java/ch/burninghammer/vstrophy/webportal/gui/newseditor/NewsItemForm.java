/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.newseditor;

import ch.burninghammer.vstrophy.webportal.entities.news.NewsItem;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.ButtonProperties;
import org.vaadin.addon.cdiproperties.annotation.FormLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.RichTextAreaProperties;
import org.vaadin.addon.cdiproperties.annotation.TextFieldProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsItemForm extends ViewComponent {

    @PropertyId("title")
    @Inject
    @TextFieldProperties(immediate = true)
    private TextField titleTextField;

    @Inject
    @PropertyId("text")
    @RichTextAreaProperties(immediate = true)
    private RichTextArea textTextArea;

    @Inject
    @FormLayoutProperties()
    private FormLayout formLayout;

    @Inject
    @ButtonProperties(enabled = false, caption = "Speichern")
    private Button button;

    private FieldGroup fieldGroup;

    private NewsItem newsItem;

    @PostConstruct
    public void init() {
        formLayout.addComponent(titleTextField);
        formLayout.addComponent(textTextArea);
        setCompositionRoot(formLayout);
        formLayout.addComponent(button);
        button.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                } catch (FieldGroup.CommitException ex) {
                    Logger.getLogger(NewsItemForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                fireViewEvent(NewsEditorCDIEvents.newsItemChanged, newsItem);
            }
        });
    }

    public void bindNewsItem(NewsItem newsItem) {
        fieldGroup = new FieldGroup(new BeanItem<>(newsItem));
        fieldGroup.bindMemberFields(this);
        this.newsItem = newsItem;
        button.setEnabled(true);
    }

}

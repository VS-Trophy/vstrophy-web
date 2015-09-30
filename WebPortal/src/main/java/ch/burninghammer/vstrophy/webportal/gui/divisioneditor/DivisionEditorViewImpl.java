/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.divisioneditor;

import ch.burninghammer.vstrophy.entities.divisions.Division;
import ch.burninghammer.vstrophy.entities.news.NewsItem;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
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
public class DivisionEditorViewImpl extends AbstractMVPView implements DivisionEditorView {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DivisionEditorViewImpl.class);
    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;
    @Inject
    @HorizontalLayoutProperties(width = "100%", margin = true)
    private HorizontalLayout mainLayout;
    @Inject
    @TableProperties(immediate = true, sizeUndefined = true, pageLength = 10)
    private Table divisionTable;

    @Inject
    @VerticalLayoutProperties(sizeUndefined = true, height = "80%")
    private VerticalLayout tableLayout;

    @Inject
    @ButtonProperties(caption = "Neu")
    private Button newDivisionButton;

    @Inject
    private DivisionForm form;

    @Override
    public void showDivisionList(List<Division> divisions) {
        BeanItemContainer<Division> divisionBeanContainer = new BeanItemContainer<>(Division.class);
        divisionBeanContainer.addAll(divisions);
        try {
            divisionTable.setContainerDataSource(divisionBeanContainer);
        } catch (Table.CacheUpdateException ex) {
            LOGGER.warn("Error during division table cache update. Ignoring as this might happen with incomplete teams.");
        }
        divisionTable.setVisibleColumns("name");
        newDivisionButton.setEnabled(true);
        mainLayout.removeComponent(form);
    }

    @PostConstruct
    private void initView() {
        setSizeFull();
        this.setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        divisionTable.addValueChangeListener(new DivisionValueChangedListener());
        divisionTable.setSelectable(true);
        tableLayout.addComponent(divisionTable);
        tableLayout.addComponent(newDivisionButton);
        newDivisionButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                event.getButton().setEnabled(false);
                fireViewEvent(DivisionEditorCDIEvents.DIVISION_ADD, null);
            }
        });
        mainLayout.addComponent(tableLayout);

    }

    @Override
    public void showDivision(Division division) {
        mainLayout.addComponent(form);
        mainLayout.setExpandRatio(form, 0.8f);
        form.bindDivision(division);
    }

    private class DivisionValueChangedListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty().getValue() != null) {
                BeanItem<NewsItem> item = (BeanItem) divisionTable.getItem(event.getProperty().getValue());
                fireViewEvent(DivisionEditorCDIEvents.DIVISION_SELECTED, item.getBean());
            }
        }
    }
}

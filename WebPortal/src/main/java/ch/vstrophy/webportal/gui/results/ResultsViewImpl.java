/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.results;

import ch.vstrophy.entities.match.Match;
import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdiproperties.annotation.ComboBoxProperties;
import org.vaadin.addon.cdiproperties.annotation.HorizontalLayoutProperties;
import org.vaadin.addon.cdiproperties.annotation.ListSelectProperties;
import org.vaadin.addon.cdiproperties.annotation.PanelProperties;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class ResultsViewImpl extends AbstractMVPView implements ResultsView {

    @Inject
    @PanelProperties(sizeFull = true)
    private Panel mainPanel;
    @Inject
    @VerticalLayoutProperties(width = "100%", margin = true, spacing = true)
    private VerticalLayout mainLayout;

    @Inject
    @HorizontalLayoutProperties(sizeFull = true, spacing = true)
    private HorizontalLayout contentLayout;

    @Inject
    @VerticalLayoutProperties(sizeFull = true, spacing = true)
    private VerticalLayout matchListLayout;

    @Inject
    @ComboBoxProperties(caption = "Saison", immediate = true)
    private ComboBox seasonCombobox;

    @Inject
    @ListSelectProperties(visible = false, nullSelectionAllowed = false, immediate = true)
    private ListSelect weekSelect;

    @PostConstruct
    private void init() {
        setSizeFull();
        setCompositionRoot(mainPanel);
        mainPanel.setContent(mainLayout);
        mainLayout.addComponent(seasonCombobox);
        mainLayout.addComponent(contentLayout);

        contentLayout.addComponent(weekSelect);
        contentLayout.addComponent(matchListLayout);
        contentLayout.setExpandRatio(matchListLayout, 0.75f);
        mainLayout.setComponentAlignment(seasonCombobox, Alignment.MIDDLE_LEFT);

        seasonCombobox.addValueChangeListener(new SeasonComboboxValueChangeListener());
        weekSelect.addValueChangeListener(new WeekListValueChangeListener());
    }

    @Override
    public void setSeasonList(List<String> seasonList) {
        seasonCombobox.removeAllItems();
        for (String season : seasonList) {
            seasonCombobox.addItem(season);
        }
        weekSelect.setVisible(false);
        matchListLayout.setVisible(false);
    }

    @Override
    public void setWeekList(List<String> weekList) {
        weekSelect.removeAllItems();
        for (String week : weekList) {
            weekSelect.addItem(week);
        }
        weekSelect.setRows(weekList.size());
        weekSelect.setVisible(true);
        matchListLayout.setVisible(false);
        if(!weekList.isEmpty()){
            weekSelect.select(weekList.get(0));
        }
    }

    @Override
    public void setMatchList(List<Match> matchList) {
        matchListLayout.removeAllComponents();
        for (Match match : matchList) {
            MatchComponent component = new MatchComponent(match);
            component.setWidth("60%");
            matchListLayout.addComponent(component);
        }
        matchListLayout.setVisible(true);
    }

    private class SeasonComboboxValueChangeListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            fireViewEvent(ResultsViewCDIEvents.SEASON_SELECTED, event.getProperty().getValue());
        }
    }

    private class WeekListValueChangeListener implements Property.ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            fireViewEvent(ResultsViewCDIEvents.WEEK_SELECTED, event.getProperty().getValue(), seasonCombobox.getValue());
        }

    }
}

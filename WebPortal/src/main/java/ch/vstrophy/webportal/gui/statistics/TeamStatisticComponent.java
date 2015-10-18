/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.statistics;

import ch.vstrophy.statistic.StatisticCategory;
import ch.vstrophy.statistic.StatisticPoint;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import java.util.List;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamStatisticComponent extends CustomComponent {

    private final Accordion mainAccordion;

    public TeamStatisticComponent() {
        mainAccordion = new Accordion();
        setupLayout();
    }

    public TeamStatisticComponent(List<StatisticCategory> statistics) {
        mainAccordion = new Accordion();
        setupLayout();
        setUpAccordion(statistics);
    }

    private void setupLayout() {
        setCompositionRoot(mainAccordion);
        mainAccordion.setSizeFull();
    }

    public void setStatistics(List<StatisticCategory> statistics) {
        setUpAccordion(statistics);
    }

    private void setUpAccordion(List<StatisticCategory> statistics) {
        mainAccordion.removeAllComponents();
        for (StatisticCategory category : statistics) {
            FormLayout statisticPointLayout = new FormLayout();
            statisticPointLayout.setMargin(true);
            for (StatisticPoint point : category.getStatisticPoints()) {
                Label statisticPointLabel = new Label();
                statisticPointLabel.setCaption(point.getName());
                statisticPointLabel.setValue(point.toOutput());
                statisticPointLayout.addComponent(statisticPointLabel);
            }
            mainAccordion.addTab(statisticPointLayout, category.getName());
        }
    }

}

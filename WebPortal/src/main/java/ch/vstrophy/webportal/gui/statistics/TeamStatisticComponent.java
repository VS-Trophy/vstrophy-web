/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.statistics;

import ch.vstrophy.statistic.StatisticCategory;
import ch.vstrophy.statistic.StatisticPoint;
import ch.vstrophy.webportal.gui.theme.VSTrophyTheme;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.ViewComponent;
import org.vaadin.addon.cdiproperties.annotation.VerticalLayoutProperties;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamStatisticComponent extends ViewComponent {

    @Inject
    @VerticalLayoutProperties(margin = true)
    private VerticalLayout mainLayout;

    @PostConstruct
    private void init() {
        setCompositionRoot(mainLayout);
    }

    public void setStatistics(List<StatisticCategory> statistics) {
        setUpAccordion(statistics);
    }

    private void setUpAccordion(List<StatisticCategory> statistics) {
        mainLayout.removeAllComponents();
        for (StatisticCategory category : statistics) {
            FormLayout statisticPointLayout = new FormLayout();
            statisticPointLayout.setMargin(false);
            statisticPointLayout.setSpacing(false);
            Label title = new Label(category.getName());
            title.setStyleName(VSTrophyTheme.LABEL_BOLD);
            mainLayout.addComponent(title);
            for (StatisticPoint point : category.getStatisticPoints()) {
                Label statisticPointLabel = new Label();
                statisticPointLabel.setCaption(point.getName());
                statisticPointLabel.setValue(point.toOutput());
                statisticPointLayout.addComponent(statisticPointLabel);
            }
            mainLayout.addComponent(statisticPointLayout);
        }
    }

}

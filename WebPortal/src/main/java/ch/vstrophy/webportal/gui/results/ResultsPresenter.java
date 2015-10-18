/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.results;

import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.webportal.data.WeekHandler;
import ch.vstrophy.common.WeekInfoProvider;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(ResultsView.class)
public class ResultsPresenter extends AbstractMVPPresenter<ResultsView> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ResultsPresenter.class);
    private static final int FIRST_SEASON = 2012;
    private static final int LAST_SEASON = 2015;
    @Inject
    private WeekHandler weekHandler;
    private List<String> seasonList;
    @Inject
    private WeekInfoProvider weekInfoProvider;

    @PostConstruct
    private void init() {
        seasonList = new ArrayList<>();
        for (int season = FIRST_SEASON; season <= LAST_SEASON; season++) {
            seasonList.add(String.valueOf(season));
        }
    }

    protected void seasonSelected(
            @Observes @CDIEvent(ResultsViewCDIEvents.SEASON_SELECTED) final ParameterDTO parameters) {
        try {
            int currentSeason = weekInfoProvider.getCurrentSeasonNumber();
            int currentWeek = weekInfoProvider.getCurrentWeekNumber();
            int season = Integer.parseInt(parameters.getPrimaryParameter(String.class));
            List<String> weekList = new ArrayList<>();
            for (Week week : weekHandler.getWeeksOfSeason(season)) {
                if (season != currentSeason || week.getNumber() <= currentWeek) {
                    weekList.add(String.valueOf(week.getNumber()));
                }

            }
            view.setWeekList(weekList);
        } catch (NumberFormatException ex) {
            LOGGER.error("Could not cast season {0}", parameters.getPrimaryParameter(String.class));
        }
    }

    protected void weekSelected(
            @Observes @CDIEvent(ResultsViewCDIEvents.WEEK_SELECTED) final ParameterDTO parameters) {
        try {
            int weekNumber = Integer.parseInt(parameters.getPrimaryParameter(String.class));
            int seasonNumber = Integer.parseInt(parameters.getSecondaryParameter(0, String.class));
            view.setMatchList(weekHandler.getMatchesOfWeek(seasonNumber, weekNumber));
        } catch (NumberFormatException ex) {
            LOGGER.error("Could not cast season {0}", parameters.getPrimaryParameter(String.class));
        }
    }

    @Override
    public void viewEntered() {

        view.setSeasonList(seasonList);
    }

}

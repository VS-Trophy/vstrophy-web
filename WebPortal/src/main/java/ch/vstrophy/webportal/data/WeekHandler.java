/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.data;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.entities.weeks.WeekEntityManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class WeekHandler {

    @Inject
    private WeekEntityManager weekEntityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Week> getWeeksOfSeason(final int season) {
        return weekEntityManager.getWeeksOfSeason(season);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Match> getMatchesOfWeek(final int season, final int week) {
        List<Match> matchList = weekEntityManager.getWeek(season, week).getMatches();
        //This is necessary because of lazy loading. The UI has no transaction so the lazy load always fails
        List<Match> returnList = new ArrayList<>();
        returnList.addAll(matchList);
        return returnList;
    }

}

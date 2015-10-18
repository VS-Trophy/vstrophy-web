/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.common;

import ch.vstrophy.entities.weeks.WeekEntityManager;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class WeekInfoProvider {

    @Inject
    private WeekEntityManager weekEntityManager;

    private static final DateTime FANTASY_SEAON_WEEK_1_2015 = new DateTime(2015, 9, 8, 5, 0);
    private static final int CURRENT_SEASON = 2015;

    public int getCurrentWeekNumber() {
        int week = 0;
        DateTime dateTime = FANTASY_SEAON_WEEK_1_2015;
        while (dateTime.isBefore(DateTime.now()) && week < 18) {
            dateTime = dateTime.plus(Weeks.ONE);
            ++week;
        }
        return week;
    }

    public int getCurrentSeasonNumber() {

        return CURRENT_SEASON;
    }

}

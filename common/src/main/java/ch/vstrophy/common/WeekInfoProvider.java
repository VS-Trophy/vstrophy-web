/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.common;


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

  

    private static final DateTime FANTASY_SEAON_WEEK_1_2018 = new DateTime(2018, 9, 4, 5, 0);
    public static final int CURRENT_SEASON = 2018;

    public int getCurrentWeekNumber() {
        int week = 0;
        DateTime dateTime = FANTASY_SEAON_WEEK_1_2018;
        while (dateTime.isBefore(DateTime.now()) && week < 17) {
            dateTime = dateTime.plus(Weeks.ONE);
            ++week;
        }
        return week;
    }

    public int getCurrentSeasonNumber() {

        return CURRENT_SEASON;
    }

}

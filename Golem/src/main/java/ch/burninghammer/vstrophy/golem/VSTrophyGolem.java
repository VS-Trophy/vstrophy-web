/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.golem;

import ch.burninghammer.vstrophy.entities.match.Match;
import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.entities.weeks.Week;
import ch.burninghammer.vstrophy.golem.exception.GolemParserException;
import ch.burninghammer.vstrophy.golem.parsers.HistoryViewParser;
import ch.burninghammer.vstrophy.golem.persistence.PersistenceHandler;
import java.io.IOException;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Default
@LocalBean
@Stateless
public class VSTrophyGolem {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VSTrophyGolem.class);
    private static final String SEASON_PLACEHOLDER = "{SEASONBR}";
    private static final String WEEK_PLACEHOLDER = "{WEEKNBR}";
    private static final String HISTORY_ADDRESS = "http://fantasy.nfl.com/league/1268875/history/" + SEASON_PLACEHOLDER + "/schedule?scheduleDetail=" + WEEK_PLACEHOLDER + "&scheduleType=week&standingsTab=schedule";
    private static final String NFL_LOGIN_ADDRESS = "https://id2.s.nfl.com/fans/login?s=fantasy&returnTo=http%3A%2F%2Fvstrophy.league.fantasy.nfl.com%2F";
    private static final String USERNAME = "vstrophy";
    private static final String PASSWORD = "g04l3m080815";

    private static final int CURRENT_SEASON = 2015;
    private static final DateTime FANTASY_SEAON_WEEK_1_2015 = new DateTime(2015, 9, 8, 5, 0);

    @Inject
    private HistoryViewParser historyViewParser;

    @Inject
    private PersistenceHandler persistenceHandler;

    public void updateCurrentWeek() throws IOException {
        int week = getCurrentWeekNumber();
        Map<String, String> cookies = login();
        updateWeek(CURRENT_SEASON, week, cookies, persistenceHandler.getTeamMap());
    }

    public void getHistoricData() throws IOException {

        Map<String, String> cookies = login();
        for (int season = 2012; season < 2016; season++) {
            for (int weekNumber = 1; weekNumber < 18; weekNumber++) {

                updateWeek(season, weekNumber, cookies, persistenceHandler.getTeamMap());
                try {
                    long timeout = 2000;
                    LOGGER.info("Waiting for " + timeout + "ms");
                    Thread.sleep(timeout);
                } catch (InterruptedException ex) {
                    LOGGER.info("interupted");
                }
            }
        }

    }

    private Map<String, String> login() throws IOException {
        Connection.Response res = Jsoup.connect(NFL_LOGIN_ADDRESS)
                .data("username", USERNAME, "password", PASSWORD)
                .method(Method.POST)
                .execute();
        return res.cookies();
    }

    private int getCurrentWeekNumber() {
        int week = 0;
        DateTime dateTime = FANTASY_SEAON_WEEK_1_2015;
        while (dateTime.isBefore(DateTime.now()) && week < 18) {
            dateTime = dateTime.plus(Weeks.ONE);
            ++week;
        }
        return week;
    }

    private void updateWeek(int season, int weekNumber, Map<String, String> cookies, Map<Integer, Team> teamMap) throws IOException {
        LOGGER.info("Updating " + season + "-" + weekNumber);
        String address = HISTORY_ADDRESS.replace(SEASON_PLACEHOLDER, String.valueOf(season)).replace(WEEK_PLACEHOLDER, String.valueOf(weekNumber));
        Document doc = Jsoup.connect(address).
                cookies(cookies)
                .get();
        Week week = persistenceHandler.getOrCreateWeek(season, weekNumber);
        try {
            historyViewParser.setHistoryViewDocument(doc);
            int matchCount = historyViewParser.getMatchCount();
            LOGGER.info("Found " + matchCount + "matches.");
            for (int idx = 0; idx < historyViewParser.getMatchCount(); ++idx) {
                LOGGER.info("Getting match #" + idx);
                Team firstTeam = teamMap.get(historyViewParser.getFirstTeamID(idx));
                Team secondTeam = teamMap.get(historyViewParser.getSecondTeamID(idx));
                Match match = persistenceHandler.getOrCreateMatch(week, firstTeam, secondTeam);
                match.setFirstTeamPoints(historyViewParser.getFirstTeamPoints(idx));
                match.setSecondTeamPoints(historyViewParser.getSecondTeamPoints(idx));
            }
        } catch (GolemParserException ex) {
            LOGGER.error("Problem parsing the site.", ex);
        }
    }
}

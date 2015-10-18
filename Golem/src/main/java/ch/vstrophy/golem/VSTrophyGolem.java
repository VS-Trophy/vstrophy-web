/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.golem;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.golem.exception.GolemException;
import ch.vstrophy.golem.exception.GolemParserException;
import ch.vstrophy.golem.parsers.HistoryViewParser;
import ch.vstrophy.golem.persistence.PersistenceHandler;
import ch.vstrophy.common.WeekInfoProvider;
import java.io.IOException;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
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

    @Inject
    private WeekInfoProvider weekInfoProvider;

    @Inject
    private HistoryViewParser historyViewParser;

    @Inject
    private PersistenceHandler persistenceHandler;

    public void updateCurrentWeek() throws IOException {
        int week = weekInfoProvider.getCurrentWeekNumber();
        Map<String, String> cookies = login();
        try {
            updateWeek(CURRENT_SEASON, week, cookies, persistenceHandler.getTeamMap());
        } catch (GolemException ex) {
            LOGGER.error("Could not update current week", ex);
        }
    }

    public void getHistoricData() throws IOException {

        Map<String, String> cookies = login();
        for (int season = 2012; season < 2016; season++) {
            for (int weekNumber = 1; weekNumber < 18; weekNumber++) {
                try {
                    updateWeek(season, weekNumber, cookies, persistenceHandler.getTeamMap());
                } catch (GolemException ex) {
                    LOGGER.error("Problem updating week " + weekNumber + " " + season, ex);
                }
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

    private void updateWeek(int season, int weekNumber, Map<String, String> cookies, Map<Integer, Team> teamMap) throws IOException, GolemException {
        LOGGER.info("Updating " + season + "-" + weekNumber);
        String address = HISTORY_ADDRESS.replace(SEASON_PLACEHOLDER, String.valueOf(season)).replace(WEEK_PLACEHOLDER, String.valueOf(weekNumber));
        Document doc = Jsoup.connect(address).
                cookies(cookies)
                .get();
        try {
            historyViewParser.setHistoryViewDocument(doc);
            int matchCount = historyViewParser.getMatchCount();
            LOGGER.info("Found " + matchCount + "matches.");
            if (matchCount > 0) {
                Week week = persistenceHandler.getOrCreateWeek(season, weekNumber);
                for (int idx = 0; idx < historyViewParser.getMatchCount(); ++idx) {
                    LOGGER.info("Getting match #" + idx);
                    Team firstTeam = teamMap.get(historyViewParser.getFirstTeamID(idx));
                    if (firstTeam == null) {
                        throw new GolemException("First team is null!");
                    }
                    Team secondTeam = teamMap.get(historyViewParser.getSecondTeamID(idx));
                    if (secondTeam == null) {
                        throw new GolemException("Second team is null!");
                    }
                    Match match = persistenceHandler.getOrCreateMatch(week, firstTeam, secondTeam);
                    match.setFirstTeamPoints(historyViewParser.getFirstTeamPoints(idx));
                    match.setSecondTeamPoints(historyViewParser.getSecondTeamPoints(idx));
                }
            }
        } catch (GolemParserException ex) {
            LOGGER.error("Problem parsing the site.", ex);
        }
    }
}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.golem;

import ch.vstrophy.common.WeekInfoProvider;
import static ch.vstrophy.common.WeekInfoProvider.CURRENT_SEASON;
import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.golem.entities.Cookie;
import ch.vstrophy.golem.entities.CookieResponse;
import ch.vstrophy.golem.exception.GolemException;
import ch.vstrophy.golem.parsers.HistoryViewParser;
import ch.vstrophy.golem.persistence.PersistenceHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
  private static final String HISTORY_ADDRESS = "http://fantasy.nfl.com/league/1268875/history/" + SEASON_PLACEHOLDER + "/schedule?scheduleDetail=" + WEEK_PLACEHOLDER + "&scheduleType=week&standingsTab=schedule&gameSeason=" + SEASON_PLACEHOLDER + "&leagueId=1268875";
  private static final String NFL_LOGIN_ADDRESS = "https://api.nfl.com/v1/reroute";
  private static final String NFL_COOKIE_ADDRESS = "https://api.nfl.com/v1/cookie";
  private static final String USERNAME = "vstrophy";
  private static final String PASSWORD = "g04l3m080815";
  private static final Pattern TOKEN_PATTERN = Pattern.compile("\"access_token\":\"([^\"]*)\"");

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
      LOGGER.info("Updating latest week");
      updateWeek(CURRENT_SEASON, week, cookies, persistenceHandler.getTeamMap());
      if (week > 1) {
        LOGGER.info("Updating last week as well");
        updateWeek(CURRENT_SEASON, week - 1, cookies, persistenceHandler.getTeamMap());
      }
    } catch (GolemException ex) {
      LOGGER.error("Could not update current week", ex);
    }
  }

  public void getHistoricData() throws IOException {

    Map<String, String> cookies = login();
    for (int season = 2012; season < 2018; season++) {
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
    LOGGER.info("LOGGING IN");
    try{
    Connection.Response loginResponse = Jsoup.connect(NFL_LOGIN_ADDRESS)
        .header("x-domain-id", "100")
        .header("content-type", "application/x-www-form-urlencoded")
        .data("username", USERNAME)
        .data("password", PASSWORD)
        .data("grant_type", "password")
        .ignoreContentType(true)
        .method(Method.POST)
        .execute();

    Connection.Response cookieResp = Jsoup.connect(NFL_COOKIE_ADDRESS)
        .header("Authorization", "Bearer " + getToken(loginResponse.body()))
        .method(Method.GET)
        .ignoreContentType(true)
        .execute();
    ObjectMapper mapper = new ObjectMapper();

    CookieResponse cookieResponse
        = mapper.readValue(cookieResp.body(), CookieResponse.class);
    Map<String, String> cookies = new HashMap<>();
    for (Cookie cookie : cookieResponse.getCookies()) {
      cookies.put(cookie.getName(), cookie.getValue());
    }
    LOGGER.info("LOGGED IN GOT " + cookies.size() + " COOKIES");
    return cookies;
    }catch(Exception ex){
      LOGGER.error("ERROR LOGGING IN",ex);
      throw ex;
    }
  }

  private String getToken(String responseBody) {
    Matcher tokenMatcher = TOKEN_PATTERN.matcher(responseBody);
    tokenMatcher.find();
    return tokenMatcher.group(1);
  }

  private void updateWeek(int season, int weekNumber, Map<String, String> cookies, Map<Integer, Team> teamMap) throws IOException, GolemException {
    LOGGER.info("Updating " + season + "-" + weekNumber);
    String address = HISTORY_ADDRESS.replace(SEASON_PLACEHOLDER, String.valueOf(season)).replace(WEEK_PLACEHOLDER, String.valueOf(weekNumber));
    Document doc = Jsoup.connect(address)
        .cookies(cookies)
        .get();
    try {
      historyViewParser.setHistoryViewDocument(doc);
      int matchCount = historyViewParser.getMatchCount();
      LOGGER.info("Found " + matchCount + "matches.");
      if (matchCount > 0) {
        persistenceHandler.getOrCreateWeek(season, weekNumber);
        for (int idx = 0; idx < historyViewParser.getMatchCount(); ++idx) {
          LOGGER.info("Getting match #" + idx);
          String firstTeamId = historyViewParser.getFirstTeamID(idx);
          String secondTeamId = historyViewParser.getSecondTeamID(idx);
          
          Match match = new Match();
          match.setFirstTeamId(firstTeamId);
          match.setSecondTeamId(secondTeamId);
              
          match.setFirstTeamPoints(historyViewParser.getFirstTeamPoints(idx));
          match.setSecondTeamPoints(historyViewParser.getSecondTeamPoints(idx));
          
          persistenceHandler.updateOrCreateMatch(season, weekNumber, match);

          LOGGER.info("Match filled out {}-{}", match.getFirstTeamPoints(), match.getSecondTeamPoints());
        }
      }
    } catch (Exception ex) {
      LOGGER.error("Problem parsing the site.", ex);
    }
  }

}

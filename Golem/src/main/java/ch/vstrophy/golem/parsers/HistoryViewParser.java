/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.golem.parsers;

import ch.vstrophy.golem.exception.GolemParserException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateful
@LocalBean
public class HistoryViewParser {

    private static final String MATCHUP_CLASS = "matchup";
    private static final String FIRST_TEAM_CLASS = "teamWrap-1";
    private static final String SECOND_TEAM_CLASS = "teamWrap-2";
    private static final String TEAM_POINT_CLASS = "teamTotal";
    private static final String TEAM_ID_PREFIX = "teamId-";

    private Document historyViewDocument;
    private List<Element> matchups = new ArrayList<>();

    public Document getHistoryViewDocument() {
        return historyViewDocument;
    }

    public void setHistoryViewDocument(Document historyViewDocument) {
        this.historyViewDocument = historyViewDocument;
        matchups = null;
    }

    public int getMatchCount() throws GolemParserException {
        if (historyViewDocument == null) {
            throw new GolemParserException("Document has not been set");
        }
        if (matchups == null) {
            loadMatchups();
        }
        return matchups.size();
    }

    public int getFirstTeamID(int matchupIdx) throws GolemParserException {
        if (historyViewDocument == null) {
            throw new GolemParserException("Document has not been set");
        }
        Element matchup = getMatchup(matchupIdx);
        Element teamWrap = getOneElementByClass(matchup, FIRST_TEAM_CLASS);
        return getTeamIdFromTeamWrap(teamWrap);
    }

    public int getSecondTeamID(int matchupIdx) throws GolemParserException {
        if (historyViewDocument == null) {
            throw new GolemParserException("Document has not been set");
        }
        Element matchup = getMatchup(matchupIdx);
        Element teamWrap = getOneElementByClass(matchup, SECOND_TEAM_CLASS);
        return getTeamIdFromTeamWrap(teamWrap);
    }

    public double getFirstTeamPoints(int matchupIdx) throws GolemParserException {
        if (historyViewDocument == null) {
            throw new GolemParserException("Document has not been set");
        }
        Element matchup = getMatchup(matchupIdx);
        Element teamWrap = getOneElementByClass(matchup, FIRST_TEAM_CLASS);
        return getPointsFromTeamWrap(teamWrap);
    }

    public double getSecondTeamPoints(int matchupIdx) throws GolemParserException {
        if (historyViewDocument == null) {
            throw new GolemParserException("Document has not been set");
        }
        Element matchup = getMatchup(matchupIdx);
        Element teamWrap = getOneElementByClass(matchup, SECOND_TEAM_CLASS);
        return getPointsFromTeamWrap(teamWrap);
    }

    private void loadMatchups() {
        if (matchups == null) {
            matchups = historyViewDocument.getElementsByClass(MATCHUP_CLASS);
        }
    }

    private Element getMatchup(int idx) throws GolemParserException {
        if (matchups == null) {
            loadMatchups();
        }
        if (matchups.size() <= idx) {
            throw new GolemParserException("Cannot get matchup with index " + idx + ". Only " + matchups.size() + " matchups loaded");
        }
        return matchups.get(idx);
    }

    private Element getOneElementByClass(Element parent, String cssClass) throws GolemParserException {
        List<Element> children = parent.getElementsByClass(cssClass);
        if (children.size() != 1) {
            throw new GolemParserException("Element " + parent.toString() + " has " + children.size() + "sub elements of class " + cssClass + " wher only one was expected");
        }
        return children.get(0);
    }

    private int getTeamIdFromTeamWrap(Element teamWrap) throws GolemParserException {
        Element teamPoint = getOneElementByClass(teamWrap, TEAM_POINT_CLASS);
        for (String className : teamPoint.classNames()) {
            if (className.startsWith(TEAM_ID_PREFIX)) {
                try {
                    //As the class name starts with the prefix, we just take everything after the prefix
                    return Integer.parseInt(className.substring(TEAM_ID_PREFIX.length()));
                } catch (NumberFormatException ex) {
                    throw new GolemParserException("Could not parse ID " + ex);
                }
            }
        }
        throw new GolemParserException("Could not get team id from teamWrap " + teamWrap.toString());
    }

    private double getPointsFromTeamWrap(Element teamWrap) throws GolemParserException {
        Element teamPoint = getOneElementByClass(teamWrap, TEAM_POINT_CLASS);
        try {
            return Double.parseDouble(teamPoint.text());
        } catch (NumberFormatException ex) {
            throw new GolemParserException("Could not parse points " + ex);
        }
    }
}

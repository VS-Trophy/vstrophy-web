/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem.persistence;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
public class Queries {

  protected static final String GET_WEEK
      = "FOR season IN Seasons "
      + "FILTER season.number == @season "
      + "FOR week IN 1..1 OUTBOUND season WeeksInSeason "
      + "FILTER week.number == @week "
      + "RETURN week";

  protected static final String GET_SEASON
      = "FOR season IN Seasons FILTER season.number == @season "
      + " RETURN season";

  protected static final String GET_TEAM
      = "FOR team IN VSTrophyTeams FILTER team.nflId == @nflId "
      + " RETURN team";

  protected static final String GET_SPECIFIC_MATCH
      = "FOR commonMatchInWeek IN INTERSECTION(\n"
      + "\n"
      + "//All matches of the week\n"
      + "(FOR season IN Seasons\n"
      + "FILTER  season.number == @season\n"
      + "    FOR week IN 1..1 ANY season WeeksInSeason\n"
      + "    FILTER week.number == @week\n"
      + "        FOR match IN 1..1 ANY week MatchesInWeek\n"
      + "        RETURN match),\n"
      + "\n"
      + "//All matches of the first team\n"
      + "(FOR team in VSTrophyTeams\n"
      + "FILTER team.nflId == @firstNflId\n"
      + "    FOR match IN 1..1 ANY team TeamPlayedIn\n"
      + "        RETURN match),\n"
      + "\n"
      + "//All matches of the second team        \n"
      + "(FOR team in VSTrophyTeams\n"
      + "FILTER team.nflId == @secondNflId\n"
      + "    FOR match IN 1..1 ANY team TeamPlayedIn\n"
      + "    RETURN match)) RETURN commonMatchInWeek";

}

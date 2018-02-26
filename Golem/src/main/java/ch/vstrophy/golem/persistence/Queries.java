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
  protected static final String GET_WEEK = 
      "FOR season IN Seasons "
      + "FILTER season.number == @season "
      + "FOR week IN 1..1 OUTBOUND season WeeksInSeason "
      + "FILTER week.number == @week "
      + "RETURN week";
  protected static final String GET_SEASON = 
      "FOR season IN Seasons FILTER season.number == @season "
      + " RETURN season";
}

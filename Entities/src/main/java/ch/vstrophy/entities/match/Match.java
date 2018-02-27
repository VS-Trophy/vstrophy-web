/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.entities.match;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
public class Match {
private String firstTeamId;
private String secondTeamId;
private double firstTeamPoints;
private double secondTeamPoints;

  public String getFirstTeamId() {
    return firstTeamId;
  }

  public void setFirstTeamId(String firstTeamId) {
    this.firstTeamId = firstTeamId;
  }

  public String getSecondTeamId() {
    return secondTeamId;
  }

  public void setSecondTeamId(String secondTeamId) {
    this.secondTeamId = secondTeamId;
  }

  public double getFirstTeamPoints() {
    return firstTeamPoints;
  }

  public void setFirstTeamPoints(double firstTeamPoints) {
    this.firstTeamPoints = firstTeamPoints;
  }

  public double getSecondTeamPoints() {
    return secondTeamPoints;
  }

  public void setSecondTeamPoints(double secondTeamPoints) {
    this.secondTeamPoints = secondTeamPoints;
  }



}

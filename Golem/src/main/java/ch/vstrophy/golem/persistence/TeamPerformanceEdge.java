/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem.persistence;

import com.arangodb.entity.BaseEdgeDocument;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
public class TeamPerformanceEdge extends BaseEdgeDocument {
  private double points;

  public TeamPerformanceEdge() {
  }

  public TeamPerformanceEdge(String from, String to) {
    super(from, to);
  }

  public double getPoints() {
    return points;
  }

  public void setPoints(double points) {
    this.points = points;
  }
  
  
}

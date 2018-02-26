/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.entities.seasons;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
public class Season {
  private int number;

  public Season(){
    
  }
  
  public Season(int number) {
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
  
}

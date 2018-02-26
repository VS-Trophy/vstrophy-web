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
public class GolemPersistenceException extends Exception{

  public GolemPersistenceException() {
  }

  public GolemPersistenceException(String message) {
    super(message);
  }

  public GolemPersistenceException(String message, Throwable cause) {
    super(message, cause);
  }

  public GolemPersistenceException(Throwable cause) {
    super(cause);
  }
  
}

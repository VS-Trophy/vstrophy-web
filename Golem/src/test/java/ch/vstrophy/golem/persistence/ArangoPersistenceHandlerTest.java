/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem.persistence;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
public class ArangoPersistenceHandlerTest {
  
  private ArangoPersistenceHandler arangoPersistenceHandler;
  
  @Before
  public void setup(){
    arangoPersistenceHandler = new ArangoPersistenceHandler();
    arangoPersistenceHandler.init();
  }
  
  @Test
  public void getOrCreateWeekTest() throws GolemPersistenceException{
    arangoPersistenceHandler.getOrCreateWeek(2017, 2);
  }
}

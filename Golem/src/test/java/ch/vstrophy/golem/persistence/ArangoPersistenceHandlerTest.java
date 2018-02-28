/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem.persistence;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import com.arangodb.entity.BaseDocument;
import javax.validation.constraints.AssertFalse;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
  
  @After
  public void tearDown(){
  arangoPersistenceHandler.tearDown();
  }
  
  @Test
  @Ignore("This may insert unwanted data!")
  public void getOrCreateWeekTest() throws GolemPersistenceException{
    arangoPersistenceHandler.getOrCreateWeek(2017, 2);
  }
  
  @Test
  public void getTeamTest() throws GolemPersistenceException{
    BaseDocument team = arangoPersistenceHandler.getTeam("4");
    assertFalse(team==null);
  }
  
  @Test
  public void getMatchTest() throws GolemPersistenceException{
    BaseDocument match = arangoPersistenceHandler.getMatch(2017,1,"1","2");
    //assertFalse(match==null);
  }
  
  @Test
  @Ignore("This may insert unwanted data!")
  public void saveMatchTest() throws GolemPersistenceException{
    Match match = new Match();
    match.setFirstTeamId("3");    
    match.setFirstTeamPoints(12);
    
    match.setSecondTeamId("2");
    match.setSecondTeamPoints(21);
    
    arangoPersistenceHandler.updateOrCreateMatch(2017, 3, match);
  }
}

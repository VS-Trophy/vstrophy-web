/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem;

import ch.vstrophy.common.WeekInfoProvider;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.golem.entities.Cookie;
import ch.vstrophy.golem.parsers.HistoryViewParser;
import ch.vstrophy.golem.persistence.ArangoPersistenceHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author kobashi@vstrophy.ch
 */

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class VSTrophyGolemTest {
    
    @Spy
    private WeekInfoProvider weekInfoProvider;
    
    @Spy
    private HistoryViewParser historyViewParser;
    
   
    
    @Mock
    private ArangoPersistenceHandler handler;
    
    @InjectMocks
    private VSTrophyGolem golem;
    
    @Before
    public void setup(){
      Mockito.when(handler.getTeamMap()).thenReturn(createTeamMap());
    }
    
    private Map<Integer,Team> createTeamMap(){
        Map<Integer,Team> map = new HashMap<>();
        Team team = new Team();
        team.setNflId(4);
        map.put(4, team);
        return map;
    }
    
    @Test
    //@Ignore
    public void testGolemService() throws IOException{
        golem.updateCurrentWeek();
    }
    
    @Test
    @Ignore
    public void bla() throws JsonProcessingException, IOException{
      Cookie cookie = new Cookie();
      cookie.setName("asdf");
      cookie.setValue("Bla");
      ObjectMapper mapper = new ObjectMapper();
      String gegl = mapper.writeValueAsString(cookie);
      System.out.println(gegl);
      Cookie c2 = mapper.readValue("{\"name\":\"asdf\",\"value\":\"Bla\"}", Cookie.class);
    }
    

}

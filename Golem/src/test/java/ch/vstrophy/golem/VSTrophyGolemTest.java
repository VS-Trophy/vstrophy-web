/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem;

import ch.vstrophy.common.WeekInfoProvider;
import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamEntityManager;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.golem.parsers.HistoryViewParser;
import ch.vstrophy.golem.persistence.PersistenceHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author kobashi@vstrophy.ch
 */
@RunWith(MockitoJUnitRunner.class)
public class VSTrophyGolemTest {
    
    @Spy
    private WeekInfoProvider weekInfoProvider;
    
    @Spy
    private HistoryViewParser historyViewParser;
    
   
    
    @Mock
    private PersistenceHandler handler;
    
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
    @Ignore
    public void testGolemService() throws IOException{
        golem.updateCurrentWeek();
    }
    

}

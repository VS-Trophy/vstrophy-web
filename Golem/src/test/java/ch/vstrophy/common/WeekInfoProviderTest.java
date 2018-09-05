/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.common;

import org.junit.Test;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
public class WeekInfoProviderTest {
@Test
public void getCurrentWeekNumber(){
  WeekInfoProvider provider = new WeekInfoProvider();
  System.out.println(provider.getCurrentSeasonNumber());
  System.out.println(provider.getCurrentWeekNumber());
}
}


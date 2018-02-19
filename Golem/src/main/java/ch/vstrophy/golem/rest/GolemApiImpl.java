/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem.rest;

import ch.vstrophy.golem.VSTrophyGolem;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Fabian Chanton <fabian.chanton@gmx.ch>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GolemApiImpl implements GolemAPI{

  @Inject
  private VSTrophyGolem golem;
  
  @Override
  public Response triggerUpdateCurrentWeek() {
   try{
    golem.updateCurrentWeek();
    return Response.noContent().build();
   } catch(Exception ex){
     return Response
         .serverError()
         .entity(ex.getMessage() + " " + ex.getCause())
         .build();
   }
  }

  @Override
  public Response triggerGetHistoricData() {
    try{
    golem.getHistoricData();
    return Response.noContent().build();
   } catch(Exception ex){
     return Response
         .serverError()
         .entity(ex.getMessage() + " " + ex.getCause())
         .build();
   }
  }

  
}

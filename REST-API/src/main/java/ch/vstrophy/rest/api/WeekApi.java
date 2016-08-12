/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api;

import ch.vstrophy.entities.weeks.Week;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;



import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Path("/week")
public interface WeekApi {
    
    @GET
    @Path("/{season}")
    @Produces("application/json")
    public Response getWeeks(@PathParam("season") int season);
}   
    

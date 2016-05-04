/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api;

import ch.vstrophy.entities.news.NewsItem;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;


import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Path("/team")
public interface TeamApi {
    
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getTeams();

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTeam(@PathParam("id") int id);

}   
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Path("/newsitem")
public interface NewsItemApi {

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getNewsItem(@PathParam("id") int id);
    
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getNewsItems(@QueryParam("limit") int limit);
    
    
    
}

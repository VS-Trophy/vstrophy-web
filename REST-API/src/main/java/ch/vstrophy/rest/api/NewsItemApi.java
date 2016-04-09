/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Path("/newsitem")
public interface NewsItemApi {
    @GET
    @Path("/")
    public Response getAllNewsItems();
}

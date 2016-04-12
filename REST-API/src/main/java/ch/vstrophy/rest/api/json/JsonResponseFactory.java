/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.json;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class JsonResponseFactory {
   
    public Response createJsonResponse(Object entity){
        return Response.ok().entity(entity).type(MediaType.APPLICATION_JSON).build();
    }
    
    public Response createIntegerResponse(Integer integer){
        return Response.ok().entity(integer).type(MediaType.TEXT_PLAIN).build();
    }
}

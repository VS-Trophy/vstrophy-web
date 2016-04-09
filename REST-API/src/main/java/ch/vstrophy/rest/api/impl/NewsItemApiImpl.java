/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.impl;

import ch.vstrophy.rest.api.NewsItemApi;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsItemApiImpl implements NewsItemApi{

    @Override
    public Response getAllNewsItems() {
       return Response.ok().entity("salä").build();
    }
    
}

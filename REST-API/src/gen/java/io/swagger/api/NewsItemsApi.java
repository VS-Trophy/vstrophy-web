package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.NewsItemsApiService;
import io.swagger.api.factories.NewsItemsApiServiceFactory;

import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/newsItems")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-04-04T18:08:29.982Z")
public class NewsItemsApi  {
   private final NewsItemsApiService delegate = NewsItemsApiServiceFactory.getNewsItemsApi();

    @GET
    
    
    
    public Response newsItemsGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.newsItemsGet(securityContext);
    }
}

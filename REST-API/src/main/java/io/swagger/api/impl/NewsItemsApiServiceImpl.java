package io.swagger.api.impl;

import ch.vstrophy.entities.news.NewsItem;
import ch.vstrophy.rest.news.NewsItemProvider;
import io.swagger.api.*;
import io.swagger.model.*;


import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.joda.time.DateTime;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-04-04T18:08:29.982Z")
public class NewsItemsApiServiceImpl extends NewsItemsApiService {

    
      @Override
      public Response newsItemsGet(SecurityContext securityContext)
      throws NotFoundException {
      NewsItem item = new NewsItem();
      item.setPublicationDate(DateTime.now().toDate());
      return Response.ok().entity(item).type(MediaType.APPLICATION_JSON).build();
  }
  
}

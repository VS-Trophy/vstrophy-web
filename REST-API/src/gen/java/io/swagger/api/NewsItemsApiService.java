package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;


import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-04-04T18:08:29.982Z")
public abstract class NewsItemsApiService {
  
      public abstract Response newsItemsGet(SecurityContext securityContext)
      throws NotFoundException;
  
}

package io.swagger.api.factories;

import io.swagger.api.NewsItemsApiService;
import io.swagger.api.impl.NewsItemsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-04-04T18:08:29.982Z")
public class NewsItemsApiServiceFactory {

   private final static NewsItemsApiService service = new NewsItemsApiServiceImpl();

   public static NewsItemsApiService getNewsItemsApi()
   {
      return service;
   }
}

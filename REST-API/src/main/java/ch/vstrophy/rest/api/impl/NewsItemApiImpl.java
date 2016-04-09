/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.impl;

import ch.vstrophy.entities.news.NewsItem;
import ch.vstrophy.entities.news.NewsItemEntityManager;
import ch.vstrophy.rest.api.NewsItemApi;
import ch.vstrophy.rest.api.json.JsonResponseFactory;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NewsItemApiImpl implements NewsItemApi{

    @Inject
    private NewsItemEntityManager entityManager;
    
    @Inject
    private JsonResponseFactory responseFactory;
    
    
    @Override
    public Response getNewsItems(int limit) {
        
        List<NewsItem> newsItems = entityManager.getMostRecentNewsItems(limit);
       return responseFactory.createJsonResponse(newsItems);
    }

    @Override
    public Response getNewsItem(int id) {
        NewsItem newsItem = entityManager.getNewsItem(id);
        return responseFactory.createJsonResponse(newsItem);
    }
    
    
    
}

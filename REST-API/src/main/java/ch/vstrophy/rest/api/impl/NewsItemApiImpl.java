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
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class NewsItemApiImpl implements NewsItemApi {
private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NewsItemApiImpl.class);
    @Inject
    private NewsItemEntityManager entityManager;

    @Inject
    private JsonResponseFactory responseFactory;

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public Response getNewsItems(int limit) {

        List<NewsItem> newsItems = entityManager.getMostRecentNewsItems(limit);
        return responseFactory.createJsonResponse(newsItems);
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public Response getNewsItem(int id) {
        NewsItem newsItem = entityManager.getNewsItem(id);
        return responseFactory.createJsonResponse(newsItem);
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public Response setNewsItem(NewsItem newsItem) {
        try {
            LOGGER.info("Version {}",newsItem.getVersion());
            newsItem = entityManager.saveNewsItem(newsItem);
            return responseFactory.createJsonResponse(newsItem);
        } catch (Exception ex) {
            LOGGER.error("Could not save news item", ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }

    }

}

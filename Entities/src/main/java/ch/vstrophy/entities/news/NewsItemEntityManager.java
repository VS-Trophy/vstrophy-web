/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.news;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class NewsItemEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    @Inject
    private NewsItemQueries queries;
    
    public List<NewsItem> getAllNewsItems() {
        return em.createQuery(
                queries.getAllNewsItems(em.getCriteriaBuilder())).
                getResultList();
    }

    public List<NewsItem> getMostRecentNewsItems(int numberOfItems) {
        return em.createQuery(
                queries.getAllNewsItems(em.getCriteriaBuilder())).
                setMaxResults(numberOfItems).
                getResultList();
    }

    public NewsItem getNewsItem(int id) {
       return em.createQuery(
               queries.getNewsItemById(em.getCriteriaBuilder(), id)).
                getSingleResult();
    }

    public NewsItem saveNewsItem(final NewsItem newsItem) {
       return em.merge(newsItem);

    }

}

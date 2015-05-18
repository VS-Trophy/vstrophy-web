/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.news;

import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateful
public class NewsItemEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    public List<NewsItem> getAllNewsItems() {
        TypedQuery<NewsItem> query = em.createQuery("SELECT n FROM NewsItem n", NewsItem.class);
        return query.getResultList();
    }

    public NewsItem createNewsItem() {
        final NewsItem newsItem = new NewsItem();
        em.persist(newsItem);
        return newsItem;
    }

    public void saveNewsItem(final NewsItem newsItem) {
        em.merge(newsItem);
    }

}

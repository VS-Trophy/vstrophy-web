/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.news;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class NewsItemPersistenceManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager entityManager;

    public void addNewsItem(NewsItem newsItem) {
        entityManager.persist(newsItem);
    }
}

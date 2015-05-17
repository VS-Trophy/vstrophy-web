/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.news;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
@TransactionManagement
public class NewsItemEntityManager {

    @PersistenceContext
    private EntityManager em;

    public List<NewsItem> getAllNewsItems() {
        TypedQuery<NewsItem> query = em.createQuery("SELECT n FROM NewsItem n", NewsItem.class);
        return query.getResultList();
    }

}

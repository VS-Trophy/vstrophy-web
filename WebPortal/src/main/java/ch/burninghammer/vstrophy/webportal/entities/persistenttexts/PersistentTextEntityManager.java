/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.persistenttexts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class PersistentTextEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    private static final String GET_PERSISTENTTEXT_QUERY = "SELECT t FROM PersistentText t WHERE t.id={}";

    public PersistentText getPersistentText(int id) {
        TypedQuery<PersistentText> query = em.createQuery(GET_PERSISTENTTEXT_QUERY.replace("{}", String.valueOf(id)), PersistentText.class);
        return query.getSingleResult();
    }

    public void savePersistentText(final PersistentText persistentText) {
        em.merge(persistentText);

    }
}

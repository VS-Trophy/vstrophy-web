/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.entities.divisions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class DivisionEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    private static final String GET_ALL_QUERY = "SELECT d FROM Division d";

    public List<Division> getAllDivisions() {
        TypedQuery<Division> query = em.createQuery(GET_ALL_QUERY, Division.class);
        return query.getResultList();
    }

    public void saveDivision(final Division division) {
        em.merge(division);

    }
}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.teams;

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
public class TeamEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    private static final String GET_ALL_QUERY = "SELECT DISTINCT t FROM Team t JOIN FETCH t.officials JOIN FETCH t.division";

    public void saveTeam(final Team team) {
        em.merge(team);
    }

    public List<Team> getAllTeams() {
        TypedQuery<Team> query = em.createQuery(GET_ALL_QUERY, Team.class);
        return query.getResultList();
    }

}

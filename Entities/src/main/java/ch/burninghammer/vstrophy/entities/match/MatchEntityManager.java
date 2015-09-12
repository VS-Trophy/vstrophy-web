/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.entities.match;

import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.entities.weeks.Week;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class MatchEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void saveMatch(final Match match) {
        if (!em.contains(match)) {
            em.persist(match);
        } else {
            em.merge(match);
        }
    }

    /**
     * Gets the specified match. The order of the teams does not matter
     *
     * @param week
     * @param firstTeam
     * @param secondTeam
     * @return
     */
    public Match getMatch(Week week, Team firstTeam, Team secondTeam) {
        CriteriaQuery<Match> query = MatchQueries.getMatchQuery(em.getCriteriaBuilder(), week, firstTeam, secondTeam);
        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}

/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.match;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class MatchEntityManager {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MatchEntityManager.class);
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

    /**
     * Gets all matches of a team
     *
     * @param team
     * @return
     */
    public List<Match> getAllMatches(Team team) {
        LOGGER.info("Starting to get all matches...");
        CriteriaQuery<Match> query = MatchQueries.getAllTeamMatches(em.getCriteriaBuilder(), team);
        try {
            List<Match> matches = em.createQuery(query).getResultList();
            LOGGER.info("...done");
            return matches;
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * Gets all matches of a season for a team
     *
     * @param team
     * @param season
     * @return
     */
    public List<Match> getMatchesForSeason(Team team, int season) {
        CriteriaQuery<Match> query = MatchQueries.getAllTeamMatchesForSeason(em.getCriteriaBuilder(), team, season);
        try {
            return em.createQuery(query).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}

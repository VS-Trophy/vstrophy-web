/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.weeks;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class WeekEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    public Week getWeek(int season, int weekNumber) {
        CriteriaQuery<Week> query = WeekQueries.getWeekQuery(em.getCriteriaBuilder(), season, weekNumber);
        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Week> getAllWeeks() {
        CriteriaQuery<Week> query = WeekQueries.getAllWeeksQuery(em.getCriteriaBuilder());
        return em.createQuery(query).getResultList();
    }

    public List<Week> getWeeksOfSeason(int season) {
        CriteriaQuery<Week> query = WeekQueries.getWeeksOfSeasonQuery(em.getCriteriaBuilder(), season);
        return em.createQuery(query).getResultList();
    }

    public void saveWeek(final Week week) {
        if (!em.contains(week)) {
            em.persist(week);
        } else {
            em.merge(week);
        }
    }
}

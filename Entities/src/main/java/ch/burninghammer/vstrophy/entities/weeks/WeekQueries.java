/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.entities.weeks;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class WeekQueries {

    public static CriteriaQuery<Week> getWeekQuery(CriteriaBuilder cb, int season, int weekNumber) {

        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<Week> q = cb.createQuery(Week.class);
        Root<Week> root = q.from(Week.class);
        predicates.add(cb.equal(root.get("season"), season));
        predicates.add(cb.equal(root.get("number"), weekNumber));
        q.select(root).where(predicates.toArray(new Predicate[]{}));
        return q;
    }

    public static CriteriaQuery<Week> getAllWeeksQuery(CriteriaBuilder cb) {
        CriteriaQuery<Week> q = cb.createQuery(Week.class);
        Root<Week> root = q.from(Week.class);
        q.select(root);
        return q;
    }

    public static CriteriaQuery<Week> getWeeksOfSeasonQuery(CriteriaBuilder cb, int season) {
        CriteriaQuery<Week> q = cb.createQuery(Week.class);
        Root<Week> root = q.from(Week.class);
        q.select(root).where(cb.equal(root.get("season"), season));
        return q;
    }
}

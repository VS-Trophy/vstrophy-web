/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.entities.match;

import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.entities.weeks.Week;
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
public class MatchQueries {

    public static CriteriaQuery<Match> getMatchQuery(CriteriaBuilder cb, Week week, Team firstTeam, Team secondTeam) {
        CriteriaQuery<Match> q = cb.createQuery(Match.class);
        Root<Match> root = q.from(Match.class);
        List<Team> teamList = new ArrayList<>();
        teamList.add(firstTeam);
        teamList.add(secondTeam);

        Predicate first = root.get("firstTeam").in(teamList);
        Predicate second = root.get("secondTeam").in(teamList);
        Predicate weekPred = cb.equal(root.get("week"), week);

        q.select(root).where(cb.and(first, second, weekPred));
        return q;
    }
}

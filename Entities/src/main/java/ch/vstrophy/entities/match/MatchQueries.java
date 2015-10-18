/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.match;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.entities.weeks.Week_;
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

        Predicate first = root.get(Match_.firstTeam).in(teamList);
        Predicate second = root.get(Match_.secondTeam).in(teamList);
        Predicate weekPred = cb.equal(root.get(Match_.week), week);

        q.select(root).where(cb.and(first, second, weekPred));
        return q;
    }

    public static CriteriaQuery<Match> getAllTeamMatches(CriteriaBuilder cb, Team team) {
        CriteriaQuery<Match> q = cb.createQuery(Match.class);
        Root<Match> root = q.from(Match.class);

        Predicate first = cb.equal(root.get(Match_.firstTeam), team);
        Predicate second = cb.equal(root.get(Match_.secondTeam), team);

        q.select(root).where(cb.or(first, second));
        return q;
    }

    public static CriteriaQuery<Match> getAllTeamMatchesForSeason(CriteriaBuilder cb, Team team, int season) {
        CriteriaQuery<Match> q = cb.createQuery(Match.class);
        Root<Match> root = q.from(Match.class);

        Predicate isFirst = cb.equal(root.get(Match_.firstTeam), team);
        Predicate isSecond = cb.equal(root.get(Match_.secondTeam), team);
        Predicate weekInSeason = cb.equal(root.get(Match_.week).get(Week_.season), season);

        q.select(root).where(cb.or(isFirst, isSecond), weekInSeason);
        return q;
    }
}

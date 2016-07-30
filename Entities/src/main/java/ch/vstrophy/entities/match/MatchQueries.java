/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.match;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.Team_;
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
        root.fetch(Match_.firstTeam).fetch(Team_.division);
        root.fetch(Match_.firstTeam).fetch(Team_.officials);
        root.fetch(Match_.secondTeam).fetch(Team_.division);
        root.fetch(Match_.secondTeam).fetch(Team_.officials);
        root.fetch(Match_.week);
        Predicate first = root.get(Match_.firstTeam).in(teamList);
        Predicate second = root.get(Match_.secondTeam).in(teamList);
        Predicate weekPred = cb.equal(root.get(Match_.week), week);

        q.select(root).where(cb.and(first, second, weekPred)).distinct(true);
        return q;
    }
    
    public static CriteriaQuery<MatchLite> getMatchLitesQuery(CriteriaBuilder cb, Week week){
        CriteriaQuery<MatchLite> q = cb.createQuery(MatchLite.class);
        Root<Match> root = q.from(Match.class);
        q.multiselect(
                root.get(Match_.firstTeam).get(Team_.id),
                root.get(Match_.secondTeam).get(Team_.id),
                root.get(Match_.firstTeamPoints), 
                root.get(Match_.secondTeamPoints));
        return q;
             
    }

    public static CriteriaQuery<Match> getMatchesQuery(CriteriaBuilder cb, Week week) {
        CriteriaQuery<Match> q = cb.createQuery(Match.class);
        Root<Match> root = q.from(Match.class);
        root.fetch(Match_.week);
        Predicate seasonPred
                = cb.equal(
                        root.get(Match_.week).get(Week_.season), week.getSeason());
        Predicate weekPred
                = cb.equal(
                        root.get(Match_.week).get(Week_.number), week.getNumber());
        

        q.select(root).where(cb.and(weekPred,seasonPred)).distinct(true);
        return q;
    }

    public static CriteriaQuery<Match> getAllTeamMatches(CriteriaBuilder cb, Team team) {
        CriteriaQuery<Match> q = cb.createQuery(Match.class);
        Root<Match> root = q.from(Match.class);
        root.fetch(Match_.firstTeam).fetch(Team_.division);
        root.fetch(Match_.firstTeam).fetch(Team_.officials);
        root.fetch(Match_.week);
        Predicate first = cb.equal(root.get(Match_.firstTeam).get(Team_.id), team.getId());
        Predicate second = cb.equal(root.get(Match_.secondTeam).get(Team_.id), team.getId());

        q.select(root).where(cb.or(first, second)).distinct(true);
        return q;
    }

    public static CriteriaQuery<Match> getAllTeamMatchesForSeason(CriteriaBuilder cb, Team team, int season) {
        CriteriaQuery<Match> q = cb.createQuery(Match.class);
        Root<Match> root = q.from(Match.class);
        root.fetch(Match_.firstTeam);
        root.fetch(Match_.firstTeam).fetch(Team_.division);
        root.fetch(Match_.firstTeam).fetch(Team_.officials);
        root.fetch(Match_.week);
        Predicate isFirst = cb.equal(root.get(Match_.firstTeam).get(Team_.id), team.getId());
        Predicate isSecond = cb.equal(root.get(Match_.secondTeam).get(Team_.id), team.getId());
        Predicate weekInSeason = cb.equal(root.get(Match_.week).get(Week_.season), season);

        q.select(root).where(cb.or(isFirst, isSecond), weekInSeason).distinct(true);
        return q;
    }
}

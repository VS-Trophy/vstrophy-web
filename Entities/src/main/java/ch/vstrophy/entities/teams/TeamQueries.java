/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.entities.teams;

import javax.enterprise.context.Dependent;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author kobashi@vstrophy.ch
 */
@Dependent
public class TeamQueries {
      public CriteriaQuery<Team> getAllTeams(CriteriaBuilder cb){
        CriteriaQuery<Team> q = cb.createQuery(Team.class);
        Root<Team> root = q.from(Team.class);
        root.fetch(Team_.division);
        root.fetch(Team_.officials);
        q.select(root).distinct(true);
        return q;
    }
      
      public CriteriaQuery<Team> getTeam(CriteriaBuilder cb,int id){
        CriteriaQuery<Team> q = cb.createQuery(Team.class);
        Root<Team> root = q.from(Team.class);
        root.fetch(Team_.division);
        root.fetch(Team_.officials);
        q.select(root).where(cb.equal(root.get(Team_.id), id)).distinct(true);
        return q;
    }

}

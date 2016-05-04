/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.teams;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class TeamEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;
    
    @Inject
    private TeamQueries teamQueries;

    public void saveTeam(final Team team) {
        em.merge(team);
    }

    public List<Team> getAllTeams() {
        CriteriaQuery<Team> critQ = 
                teamQueries.getAllTeams(em.getCriteriaBuilder());
        TypedQuery<Team> query = em.createQuery(critQ);
        return query.getResultList();
    }
    
    public Team getTeam(int id){
        CriteriaQuery<Team> critQ = 
                teamQueries.getAllTeam(em.getCriteriaBuilder(), id);
        TypedQuery<Team> query = em.createQuery(critQ);
        return query.getSingleResult();
    }
    

}

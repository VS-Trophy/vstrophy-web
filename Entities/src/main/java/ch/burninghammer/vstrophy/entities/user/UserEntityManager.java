/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.entities.user;

import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateful
public class UserEntityManager {

    @PersistenceContext(unitName = "ch.vstrophy_WebPortal_PU")
    private EntityManager em;

    public void saveUser(final User user) {
        em.merge(user);
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public User getUser(String username) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u from User u WHERE u.name=:username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}

package com.adso.services;

import com.adso.entities.User;
import com.adso.exceptions.auth.NotValidCredentials;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

public class UserAuthenticationService {
    private EntityManager em = null;

    public UserAuthenticationService() {
    	EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
    	em = emf.createEntityManager();
    }

    public User validateUser(String username, String password) throws NotValidCredentials {
        try {
            // Query the database for a user with the given email
            User user = em.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            // Check if the user exists and if the password matches
            if (user != null && user.getPassword().equals(password)) {
            	System.out.println("valido");
            	return user;
            } else {
            	throw new NotValidCredentials();
            }
        } catch (NoResultException e) {
        	throw new NotValidCredentials();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
		}
    }
    
    
}

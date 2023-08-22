package com.adso.services;

import com.adso.entities.User;
import com.adso.exceptions.auth.NotValidCredentials;
import com.adso.persistence.AppEntityManager;
import com.adso.utils.PasswordHashing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

public class UserAuthenticationService {
    private EntityManagerFactory emf;

    public UserAuthenticationService() {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();
    	
    }

    public User validateUser(String username, String password) throws NotValidCredentials {
    	EntityManager em = emf.createEntityManager();
    	
        try {
            // Query the database for a user with the given email
            User user = em.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            
            boolean passwordMatches = PasswordHashing.checkPassword(password, user.getPassword());

            // Check if the password matches
            if (passwordMatches) {
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

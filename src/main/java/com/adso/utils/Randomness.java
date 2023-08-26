package com.adso.utils;

import java.util.Random;

import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.enums.Rarity;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class Randomness {
    
    static public Rarity pickRandomRarity() {
    	Random random = new Random();
        int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
        
        if (randomNumber < 30) {
            return Rarity.FREE;					// 30% Probability
        } else if (randomNumber < 55) {
        return Rarity.COMMON;					// 25% Probability
        } else if (randomNumber < 75) {
        	return Rarity.RARE;					// 20% Probability
        } else if (randomNumber < 90) {
        	return Rarity.EPIC;					// 15% Probability
        } else {
            return Rarity.LEGENDARY;			// 10% Probability
        }
    }
    
	static public Card getRandomCardBasedOnRarity(Rarity randomRarity) throws NotResultsToShowException {
    	EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
		try {			
	        TypedQuery<Card> query = em.createQuery("FROM Card WHERE rarity = :randomRarity ORDER BY RAND()", Card.class)
	    	.setParameter("randomRarity", randomRarity)
	    	.setMaxResults(1);
	        
	        Card reedemCard = query.getSingleResult();
	        return reedemCard;
	        
		} catch (NoResultException e) {
			throw new NotResultsToShowException("Card");
		}
	}
	
	static public Pet getRandomPetBasedOnRarity(Rarity randomRarity) throws NotResultsToShowException {
    	EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
		try {			
	        TypedQuery<Pet> query = em.createQuery("FROM Pet WHERE rarity = :randomRarity ORDER BY RAND()", Pet.class)
	    	.setParameter("randomRarity", randomRarity)
	    	.setMaxResults(1);
	        
	        Pet reedemCard = query.getSingleResult();
	        return reedemCard;
	        
		} catch (NoResultException e) {
			throw new NotResultsToShowException("Pet");
		}
	}
}

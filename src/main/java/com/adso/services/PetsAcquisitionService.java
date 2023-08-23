package com.adso.services;

import java.util.Set;

import com.adso.constants.CardRarityPriceMapping;
import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.cards.NotFoundCardException;
import com.adso.exceptions.purchases.AlreadyUnlockedCardException;
import com.adso.exceptions.purchases.NotEnoughCoinsException;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PetsAcquisitionService {
    private EntityManagerFactory emf = null;

    public PetsAcquisitionService() {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();

    }
    
    public Pet purchasePet (Pet petToAcquire, Long userId) throws NotFoundCardException, UserNotFoundException, NotEnoughCoinsException, AlreadyUnlockedCardException {
    	EntityManager em = emf.createEntityManager();
    	Long petId = petToAcquire.getId();
    	
    	Pet pet = em.find(Pet.class, petId);
    	
    	if (pet == null) {
    		throw new NotFoundCardException(petId);
    	}
    	
    	User user = em.find(User.class, userId);
    	
    	if (user == null) {
    		throw new UserNotFoundException();
    	}
    	
    	Set<Pet> userPets = user.getPets();
    	Rarity cardRarity = pet.getRarity();
    	Integer petPrice = CardRarityPriceMapping.getPriceForRarity(cardRarity);
    	Integer userCoins = user.getCoins();
    	
    	// Check if user own enough coins for purchase
    	if (!(userCoins >= petPrice)) {
    		throw new NotEnoughCoinsException("Pet");
    	}
    	
    	// Check if user already owns the card
    	if (userPets.contains(pet)) {
    		throw new AlreadyUnlockedCardException();
    	}
    	    	
    	try {
    		em.getTransaction().begin();    
    		
    		userPets.add(pet);    
    		user.setPets(userPets);
    		user.setCoins(user.getCoins() - petPrice);
    		em.merge(user);
    		
    		em.getTransaction().commit();
    	} finally {
	        em.close();
	    }
    	
    	return pet;

    }
}

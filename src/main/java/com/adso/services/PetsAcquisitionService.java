package com.adso.services;

import java.util.Set;

import com.adso.constants.AppItemsPrices;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.items.AlreadyUnlockedItemException;
import com.adso.exceptions.purchases.NotEnoughCoinsException;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PetsAcquisitionService {
    private EntityManagerFactory emf = null;

    public PetsAcquisitionService() {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();

    }
    
    public Pet purchasePet (Pet petToAcquire, Long userId) throws NotEnoughCoinsException, NotFoundException, AlreadyUnlockedItemException {
    	EntityManager em = emf.createEntityManager();
    	Long petId = petToAcquire.getId();
    	
    	Pet pet = em.find(Pet.class, petId);
    	
    	if (pet == null) {
    		throw new NotFoundException("Pet with the id: " + petId);
    	}
    	
    	User user = em.find(User.class, userId);
    	
    	if (user == null) {
    		throw new NotFoundException("User with the id: " + userId);
    	}
    	
    	Set<Pet> userPets = user.getPets();
    	Rarity petRarity = pet.getRarity();
    	Integer petPrice = AppItemsPrices.getPetPriceFromRarity(petRarity);
    	Integer userCoins = user.getCoins();
    	
    	// Check if user own enough coins for purchase
    	if (!(userCoins >= petPrice)) {
    		throw new NotEnoughCoinsException("Buying pet.");
    	}
    	
    	// Check if user already owns the card
    	if (userPets.contains(pet)) {
    		throw new AlreadyUnlockedItemException("Pet.");
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

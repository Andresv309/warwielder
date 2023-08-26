package com.adso.services;

import java.util.HashSet;
import java.util.Set;

import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.entities.StoreItems;
import com.adso.enums.Rarity;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.exceptions.codeRedemption.NoCardsAvailableException;
import com.adso.persistence.AppEntityManager;
import com.adso.utils.Randomness;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class StoreRefreshItemsService {
    private static EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
    
    public static void main (String[] args) {
    	try {
			refreshItems();
		} catch (NotResultsToShowException | NoCardsAvailableException e) {
			e.printStackTrace();
		}
    }

    
    static private void refreshItems () throws NoCardsAvailableException, NotResultsToShowException {
    	EntityManager em = emf.createEntityManager();

    	
    	StoreItems storeItems = new StoreItems();
    	Set<Card> cards = new HashSet<>();
    	Set<Pet> pets  = new HashSet<>();
    	
    	for (int i = 0; i < 7; i++) {
    		Rarity randomRarity = Randomness.pickRandomRarity();
    		Card card = Randomness.getRandomCardBasedOnRarity(randomRarity);
    		cards.add(card);
    	}
    	
    	for (int i = 0; i < 1; i++) {
    		Rarity randomRarity = Randomness.pickRandomRarity();
    		Pet pet = Randomness.getRandomPetBasedOnRarity(randomRarity);
    		pets.add(pet);
    	}
    	
    	storeItems.setCards(cards);
    	storeItems.setPets(pets);
    	
    	try {
    		em.getTransaction().begin();
    		
    		em.merge(storeItems);
    		
    		em.getTransaction().commit();
    		
    	} catch(Exception e) {
    		em.getTransaction().rollback();
    		e.printStackTrace();
    	} finally {
    		em.close();
    	}
	
    }
    
}

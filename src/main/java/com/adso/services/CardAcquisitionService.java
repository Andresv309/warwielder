package com.adso.services;

import java.util.Set;

import com.adso.constants.AppItemsPrices;
import com.adso.entities.Card;
import com.adso.entities.StoreItems;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.exceptions.app.items.AlreadyUnlockedItemException;
import com.adso.exceptions.purchases.NotAllowedPurchaseException;
import com.adso.exceptions.purchases.NotEnoughCoinsException;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CardAcquisitionService {
    private EntityManagerFactory emf = null;

    public CardAcquisitionService() {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();

    }
    
    public Card purchaseCard (Card cardToAcquire, Long userId) throws NotEnoughCoinsException, NotFoundException, AlreadyUnlockedItemException, NotAllowedPurchaseException, NotResultsToShowException {
    	EntityManager em = emf.createEntityManager();
    	Long cardId = cardToAcquire.getId();
    	
    	Card card = em.find(Card.class, cardId);
    	
    	// Check if card is allowed for sell in store
    	try {
        	TypedQuery<StoreItems> countQuery  = em.createQuery("FROM StoreItems ORDER BY createdAt DESC", StoreItems.class)
        			.setMaxResults(1);
        	StoreItems storeItems = countQuery.getSingleResult();
        
        	Set<Card> storeCards = storeItems.getCards();

        	if (!storeCards.contains(card)) {
        		throw new NotAllowedPurchaseException("Card");
        	}
        	
    	} catch (NoResultException e) {
    		throw new NotResultsToShowException("Items");
    	}
    	
    	if (card == null) {
    		throw new NotFoundException("Card with the id: " + cardId);
    	}
    	
    	User user = em.find(User.class, userId);
    	
    	if (user == null) {
    		throw new NotFoundException("User with the id: " + userId);
    	}
    	
    	Set<Card> userCards = user.getCards();
    	Rarity cardRarity = card.getRarity();
    	Integer cardPrice = AppItemsPrices.getCardPriceFromRarity(cardRarity);
    	Integer userCoins = user.getCoins();
    	
    	// Check if user own enough coins for purchase
    	if (!(userCoins >= cardPrice)) {
    		throw new NotEnoughCoinsException("Buying card.");
    	}
    	
    	// Check if user already owns the card
    	if (userCards.contains(card)) {
    		throw new AlreadyUnlockedItemException("Card.");
    	}
 	
    	try {
    		em.getTransaction().begin();    
    		
    		userCards.add(card);    
    		user.setCards(userCards);
    		user.setCoins(user.getCoins() - cardPrice);
    		em.merge(user);
    		
    		em.getTransaction().commit();
    	} finally {
	        em.close();
	    }
    	
    	return card;

    }
    
}


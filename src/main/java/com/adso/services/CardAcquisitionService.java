package com.adso.services;

import java.util.Set;

import com.adso.constants.CardRarityPriceMapping;
import com.adso.entities.Card;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.cards.NotFoundCardException;
import com.adso.exceptions.purchases.NotEnoughCoinsException;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CardAcquisitionService {
    private EntityManagerFactory emf = null;

    public CardAcquisitionService() {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();

    }
    
    public Card purchaseCard (Card cardToAcquire, Long userId) throws NotFoundCardException, UserNotFoundException, NotEnoughCoinsException {
    	EntityManager em = emf.createEntityManager();
    	Long cardId = cardToAcquire.getId();
    	
    	Card card = em.find(Card.class, cardId);
    	
    	if (card == null) {
    		throw new NotFoundCardException(cardId);
    	}
    	
    	User user = em.find(User.class, userId);
    	
    	if (user == null) {
    		throw new UserNotFoundException();
    	}
    	
    	Set<Card> userCards = user.getCards();
    	Rarity cardRarity = card.getRarity();
    	Integer cardPrice = CardRarityPriceMapping.getPriceForRarity(cardRarity);
    	Integer userCoins = user.getCoins();
    	
    	// Check if user own enough coins for purchase
    	if (!(userCoins >= cardPrice)) {
    		throw new NotEnoughCoinsException("Card");
    	}
    	    	
    	try {
    		em.getTransaction().begin();    
    		
    		userCards.add(card);    		
    		em.merge(user);
    		
    		em.getTransaction().commit();
    	} finally {
	        em.close();
	    }
    	
    	return card;

    }
    
}


package com.adso.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.adso.entities.Card;
import com.adso.entities.RedemptionCode;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.codeRedemption.CodeAlreadyRedeemedException;
import com.adso.exceptions.codeRedemption.InvalidCodeException;
import com.adso.exceptions.codeRedemption.NoCardsAvailableException;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CodeRedemptionService {    
    private EntityManager em = null;

    public CodeRedemptionService() {
    	EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
    	em = emf.createEntityManager();
    }
    
    private boolean hasBeenAlreadyRedeem(String code) throws InvalidCodeException {
        Boolean isRedeem = true;
    	try {            
            RedemptionCode redemptionCode = em.createQuery("FROM RedemptionCode WHERE code = :code", RedemptionCode.class)
	    	.setParameter("code", code)
	    	.getSingleResult();
              
            isRedeem = redemptionCode.getIsRedeemed();
            
	    } catch (NoResultException e) {
	    	throw new InvalidCodeException(code);
	    }
    	
    	return isRedeem;
    }
    

    public Map<String, Object> redeemCardFromCode(String code, Long userId)
    	throws
    	InvalidCodeException,
    	CodeAlreadyRedeemedException,
    	NoCardsAvailableException,
    	UserNotFoundException
    {
    	Map<String, Object> redeemCardInfo = new HashMap<>();
    	Map<String, Object> redeemCardMessages = new HashMap<>();
    	
    	
    	if (hasBeenAlreadyRedeem(code)) {
    		throw new CodeAlreadyRedeemedException(code);
    	}
    	
        Rarity randomRarity = pickRandomRarity();
        Card redeemCard = getRandomCardBasedOnRarity(randomRarity);
        
    	User user = em.find(User.class, userId);
    	
    	if (user == null) {
    		throw new UserNotFoundException();
    	}
    	
    	boolean isCardAlreadyUnlocked = isCardAlreadyUnlocked(redeemCard, user.getCards());
        
        try {
            em.getTransaction().begin();

            RedemptionCode redemptionCode = em.createQuery("FROM RedemptionCode WHERE code = :code", RedemptionCode.class)
        	    	.setParameter("code", code)
        	    	.getSingleResult();
            
            
            redemptionCode.setIsRedeemed(true);
            redemptionCode.setUser(user);
            
            
            if (isCardAlreadyUnlocked) {
            	int redeemValue = pickRedeemValueForRarity(randomRarity);
            	user.setCoins(redeemValue + user.getCoins());
            	
            	redeemCardMessages.put("redemptionStatus", "Card already unlocked.");
            	
            } else {    
            	Set<Card> cards = user.getCards();
            	cards.add(redeemCard);
            	user.setCards(cards);
                
            	redeemCardMessages.put("redemptionStatus", "New card unlocked.");
            }
            
            redeemCardInfo.put("unlockedCard", redeemCard);
            redeemCardInfo.put("messages", redeemCardMessages);
 
            em.getTransaction().commit();
	    } catch (NoResultException e) {
	    	throw new InvalidCodeException(code);
	    } finally {
	        em.close();
	    }
    	

    	return redeemCardInfo;
    }

    private Card getRandomCardBasedOnRarity(Rarity randomRarity) throws NoCardsAvailableException {   	
		try {
			System.out.println(randomRarity);
			
	        TypedQuery<Card> query = em.createQuery("FROM Card WHERE rarity = :randomRarity ORDER BY RAND()", Card.class)
	    	.setParameter("randomRarity", randomRarity)
	    	.setMaxResults(1);
	        
	        Card reedemCard = query.getSingleResult();
	        return reedemCard;
	        
		} catch (NoResultException e) {
			throw new NoCardsAvailableException();
		}
	}
    
    private boolean isCardAlreadyUnlocked(Card redeemCard, Set<Card> unlockedCards) {
    	Long redeemCardId = redeemCard.getId();
    	boolean isAlreadyUnlocked = false;
    	
    	for (Card unlockedCard: unlockedCards) {
    		if (redeemCardId.equals(unlockedCard.getId())) {
    			isAlreadyUnlocked = true;
    			break;
    		}
    	}
    	
    	return isAlreadyUnlocked;
    }
    
    private int pickRedeemValueForRarity(Rarity randomRarity) {
    	switch (randomRarity) {
		case FREE:
			return 25;
		case COMMON:
			return 50;
		case RARE:
			return 100;
		case EPIC:
			return 200;
		case LEGENDARY:
			return 400;
		default:
			return 0;
		}
    	
    }
    
    private Rarity pickRandomRarity() {
    	Random random = new Random();
        int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
        
        if (randomNumber < 30) {
            return Rarity.FREE;				// 30% Probability
        } else if (randomNumber < 55) {
            return Rarity.COMMON;					// 25% Probability
        } else if (randomNumber < 75) {
        	return Rarity.RARE;				// 20% Probability
        } else if (randomNumber < 90) {
        	return Rarity.EPIC;					// 15% Probability
        } else {
            return Rarity.LEGENDARY;			// 10% Probability
        }
    }

}


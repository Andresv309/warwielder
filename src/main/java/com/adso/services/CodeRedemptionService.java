package com.adso.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.adso.entities.Card;
import com.adso.entities.RedemptionCode;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.exceptions.codeRedemption.InvalidCodeException;
import com.adso.persistence.AppEntityManager;
import com.adso.utils.Randomness;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

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
	    	throw new InvalidCodeException("code: " + code);
	    }
    	
    	return isRedeem;
    }
    

    public Map<String, Object> redeemCardFromCode(String code, Long userId)
    	throws
    	InvalidCodeException,
    	InvalidCodeException,
    	NotFoundException,
    	NotResultsToShowException
    {
    	Map<String, Object> redeemCardInfo = new HashMap<>();
    	Map<String, Object> redeemCardMessages = new HashMap<>();
    	
    	
    	if (hasBeenAlreadyRedeem(code)) {
    		throw new InvalidCodeException("The code: " + code + "has already been redeem.");
    	}
    	
        Rarity randomRarity = Randomness.pickRandomRarity();
        Card redeemCard = Randomness.getRandomCardBasedOnRarity(randomRarity);
        
    	User user = em.find(User.class, userId);
    	
    	if (user == null) {
    		throw new NotFoundException("User");
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
	    	throw new InvalidCodeException("code: " + code);
	    } finally {
	        em.close();
	    }
    	

    	return redeemCardInfo;
    }

//    private Card getRandomCardBasedOnRarity(Rarity randomRarity) throws NoCardsAvailableException {   	
//		try {
//			System.out.println(randomRarity);
//			
//	        TypedQuery<Card> query = em.createQuery("FROM Card WHERE rarity = :randomRarity ORDER BY RAND()", Card.class)
//	    	.setParameter("randomRarity", randomRarity)
//	    	.setMaxResults(1);
//	        
//	        Card reedemCard = query.getSingleResult();
//	        return reedemCard;
//	        
//		} catch (NoResultException e) {
//			throw new NoCardsAvailableException();
//		}
//	}
    
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
			return 500;
		case COMMON:
			return 4000;
		case RARE:
			return 8000;
		case EPIC:
			return 16000;
		case LEGENDARY:
			return 36000;
		default:
			return 0;
		}
    	
    }
    
//    private Rarity pickRandomRarity() {
//    	Random random = new Random();
//        int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
//        
//        if (randomNumber < 30) {
//            return Rarity.FREE;				// 30% Probability
//        } else if (randomNumber < 55) {
//            return Rarity.COMMON;					// 25% Probability
//        } else if (randomNumber < 75) {
//        	return Rarity.RARE;				// 20% Probability
//        } else if (randomNumber < 90) {
//        	return Rarity.EPIC;					// 15% Probability
//        } else {
//            return Rarity.LEGENDARY;			// 10% Probability
//        }
//    }

}


package com.adso.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.adso.attributeConverters.RarityConverter;
import com.adso.entities.Card;
import com.adso.entities.RedemptionCode;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.exceptions.codeRedemption.CodeAlreadyRedeemedException;
import com.adso.exceptions.codeRedemption.InvalidCodeException;
import com.adso.exceptions.codeRedemption.NoCardsAvailableException;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.persistence.AppEntityManager;
import com.adso.utils.JsonResponseBuilder;

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
    

    public String redeemCardFromCode(String code, Long userId)
    	throws
    	InvalidCodeException,
    	CodeAlreadyRedeemedException,
    	NoCardsAvailableException,
    	UserNotFoundException
    {
    	JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
    	
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
            	
            	jsonBuilder.addField("redemptionStatus", "Card already unlocked.");
            	
            } else {    
            	Set<Card> cards = user.getCards();
            	cards.add(redeemCard);
            	user.setCards(cards);
//                user.setCards(Collections.singleton(redeemCard));
                
                jsonBuilder.addField("redemptionStatus", "New card unlocked.");
            }
            
            jsonBuilder.addField("cardUnlocked", redeemCard);

	        em.getTransaction().commit();
	    } catch (NoResultException e) {
	    	throw new InvalidCodeException(code);
	    } finally {
	        em.close();
	    }
    	

    	return jsonBuilder.build();
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
		case COMMON:
			return 25;
		case RARE:
			return 50;
		case ADVANCED:
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
            return Rarity.COMMON;				// 30% Probability
        } else if (randomNumber < 55) {
            return Rarity.RARE;					// 25% Probability
        } else if (randomNumber < 75) {
        	return Rarity.ADVANCED;				// 20% Probability
        } else if (randomNumber < 90) {
        	return Rarity.EPIC;					// 15% Probability
        } else {
            return Rarity.LEGENDARY;			// 10% Probability
        }
    }

}










//package com.adso.services;
//
//import java.util.Collections;
//import java.util.Random;
//import java.util.Set;
//
//import com.adso.entities.Card;
//import com.adso.entities.RedemptionCode;
//import com.adso.entities.User;
//import com.adso.enums.Rarity;
//import com.adso.persistence.AppEntityManager;
//import com.adso.utils.JsonResponseBuilder;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.TypedQuery;
//
//public class CodeRedemptionService {    
//	private EntityManager em = null;
//	
//	public CodeRedemptionService() {
//		EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
//		em = emf.createEntityManager();
//	}
//	
//	private boolean hasBeenAlreadyRedeem(String code) {
//		Boolean isRedeem = true;
//		try {            
//			RedemptionCode redemptionCode = em.createQuery("FROM RedemptionCode WHERE code = :code", RedemptionCode.class)
//					.setParameter("code", code)
//					.getSingleResult();
//			
//			isRedeem = redemptionCode.getIsRedeemed();
//			
//		} catch (NoResultException e) {}
//		
//		return isRedeem;
//	}
//	
//	
//	public String redeemCardFromCode(String code, Long userId) {
//		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
//		
//		if (hasBeenAlreadyRedeem(code)) {
//			jsonBuilder.addField("error", "The code has already been redeem.");
//			return jsonBuilder.build();
//		}
//		
//		Rarity randomRarity = pickRandomRarity();
//		Card redeemCard = getRandomCardBasedOnRarity(randomRarity);
//		
//		if (redeemCard == null) {
//			jsonBuilder.addField("error", "No cards available.");
//			return jsonBuilder.build();
//		}
//		
//		User user = em.find(User.class, userId);
//		
//		if (user == null) {
//			jsonBuilder.addField("error", "User doesn't exists.");
//			return jsonBuilder.build();
//		}
//		
//		boolean isCardAlreadyUnlocked = isCardAlreadyUnlocked(redeemCard, user.getCards());
//		
//		try {
//			em.getTransaction().begin();
//			
//			RedemptionCode redemptionCode = em.createQuery("FROM RedemptionCode WHERE code = :code", RedemptionCode.class)
//					.setParameter("code", code)
//					.getSingleResult();
//			
//			redemptionCode.setIsRedeemed(true);
//			redemptionCode.setUser(user);
//			
//			if (isCardAlreadyUnlocked) {
//				int redeemValue = pickRedeemValueForRarity(randomRarity);
//				user.setCoins(redeemValue + user.getCoins());
//				
//				jsonBuilder.addField("redemptionStatus", "Card already unlocked.");
//				
//			} else {    
//				user.setCards(Collections.singleton(redeemCard));
//				
//				jsonBuilder.addField("redemptionStatus", "New card unlocked.");
//			}
//			
//			jsonBuilder.addField("cardUnlocked", redeemCard);
//			
//			em.getTransaction().commit();
//		} catch (NoResultException e) {
//			jsonBuilder.addField("error", "New card unlocked.");
//		} finally {
//			em.close();
//		}
//		
//		
//		return jsonBuilder.build();
//	}
//	
//	
//	private Card getRandomCardBasedOnRarity(Rarity randomRarity) {   	
//		try {
//			
//			TypedQuery<Card> query = em.createQuery("FROM Card WHERE rarity = :randomRarity" +
//					"ORDER BY RAND()", Card.class)
//					.setParameter("randomRarity", randomRarity)
//					.setMaxResults(1);
//			
//			Card reedemCard = query.getSingleResult();
//			return reedemCard;
//			
//		} catch (NoResultException e) {return null;}
//	}
//	
//	
//	
//	
//	private boolean isCardAlreadyUnlocked(Card redeemCard, Set<Card> unlockedCards) {
//		Long redeemCardId = redeemCard.getId();
//		boolean isAlreadyUnlocked = false;
//		
//		for (Card unlockedCard: unlockedCards) {
//			if (redeemCardId.equals(unlockedCard.getId())) {
//				isAlreadyUnlocked = true;
//				break;
//			}
//		}
//		
//		return isAlreadyUnlocked;
//	}
//	
//	private int pickRedeemValueForRarity(Rarity randomRarity) {
//		switch (randomRarity) {
//		case COMMON:
//			return 25;
//		case RARE:
//			return 50;
//		case ADVANCED:
//			return 100;
//		case EPIC:
//			return 200;
//		case LEGENDARY:
//			return 400;
//		default:
//			return 0;
//		}
//		
//	}
//	
//	
//	private Rarity pickRandomRarity() {
//		Random random = new Random();
//		int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
//		
//		if (randomNumber < 30) {
//			return Rarity.COMMON;				// 30% Probability
//		} else if (randomNumber < 55) {
//			return Rarity.RARE;					// 25% Probability
//		} else if (randomNumber < 75) {
//			return Rarity.ADVANCED;				// 20% Probability
//		} else if (randomNumber < 90) {
//			return Rarity.EPIC;					// 15% Probability
//		} else {
//			return Rarity.LEGENDARY;			// 10% Probability
//		}
//	}
//	
//}
//
//
//
//
//
//
//
//
//
//

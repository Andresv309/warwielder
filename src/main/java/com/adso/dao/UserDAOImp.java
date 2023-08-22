package com.adso.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;

import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.DeckCard;
import com.adso.entities.User;
import com.adso.exceptions.decks.CardAlreadyInDeckException;
import com.adso.exceptions.decks.NotValidPositionValue;
import com.adso.exceptions.user.UserAlreadyExistsException;
import com.adso.exceptions.user.UserUnauthorizedForOperationException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;


public class UserDAOImp implements UserDAO {
	
	private EntityManagerFactory emf;
	
    public UserDAOImp(EntityManagerFactory emf) {
    	this.emf = emf;
    }

	@Override
	public Set<Card> getUserUnlockedCards(Long id) {
		EntityManager em = emf.createEntityManager();

		User user = em.find(User.class, id);
		Set<Card> unlockedCards = user.getCards();

		return unlockedCards;
	}
	
	@Override
	public List<Object> getUserDecksCards(Long userId) {
		EntityManager em = emf.createEntityManager();
		
		User user = em.find(User.class, userId);
		Set<Deck> userDecks = user.getDecks();

		List<Object> deckList = new ArrayList<>();
		for (Deck deck : userDecks) {
			Map<String, Object> deckMap = new HashMap<>();
			Set<DeckCard> deckCards = deck.getDeckCards();

			List<Object> cardsList = new ArrayList<>();
			for (DeckCard deckCard : deckCards) {
				Map<String, Object> deckCardMap = new HashMap<>();
				deckCardMap.put("position", deckCard.getPosition());
				deckCardMap.put("card", deckCard.getCard());
				
				cardsList.add(deckCardMap);
			}
			
			deckMap.put("id", deck.getId());
			deckMap.put("deck", cardsList);	
			
			deckList.add(deckMap);
		}
		
		em.close();
		
		return deckList;
	}
		
	@Override
	public User addNewUser(String username, String password) throws UserAlreadyExistsException {
 
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			em.persist(newUser);
			
			em.getTransaction().commit();
			
			return newUser;

        } catch (EntityExistsException | ConstraintViolationException e) {
        	throw new UserAlreadyExistsException(username);
        } finally {
			em.close();
		}
		 
	}

	@Override
	public DeckCard updateDeck(DeckCard updateDeckCard, Long userId) throws NotValidPositionValue, UserUnauthorizedForOperationException, CardAlreadyInDeckException {
		EntityManager em = emf.createEntityManager();
		
        int position = updateDeckCard.getPosition();
        Long deckId = updateDeckCard.getDeck().getId();
        Long cardId = updateDeckCard.getCard().getId();
        
    	if (!(1 <= position && position <=8)) {
    		throw new NotValidPositionValue(position);
    	}
    	
		// Verify the user owns the deck
		Deck askedDeck = em.find(Deck.class, deckId);
		Long askedUserId = askedDeck.getUser().getId();
		if (!askedUserId.equals(userId)) {
            throw new UserUnauthorizedForOperationException("Changing Deck");
		}

		// Check if cardId is passed
		if (cardId != null) {
			// Verify the user owns the card
			User user = em.find(User.class, userId);
			Set<Card> cardsOwn = user.getCards();
			boolean userOwnsCard = false;
		    for (Card userCard : cardsOwn) {
		        if (userCard.getId().equals(cardId)) {
		            userOwnsCard = true;
		            break;
		        }
		    }
			if (!userOwnsCard) {
	            throw new UserUnauthorizedForOperationException("Adding Card");
			}
		}

		DeckCard deckCard = null;
		
		// Check if deckCard already exists.
		try {
			deckCard = em.createQuery("SELECT dc FROM DeckCard dc WHERE dc.deck.id = :deckId AND dc.position = :position", DeckCard.class)
			    	.setParameter("deckId", deckId)
			    	.setParameter("position", position)
			    	.getSingleResult();   
	        
		} catch (NoResultException e) {
			// If doesn't exists we use the information passed by the user.
			deckCard = updateDeckCard;

		} finally {
			if (cardId != null) {
				Card cardPicked = em.find(Card.class, cardId);
				deckCard.setCard(cardPicked);
			} else {
				deckCard.setCard(null);
			}
		}
		
		// Update in DB
		try {
			em.getTransaction().begin();
			DeckCard deckCardUpdated = em.merge(deckCard);
			em.getTransaction().commit();
			return deckCardUpdated;
			
		} catch (ConstraintViolationException e) {
			// If trying to add an already existing card in the deck.
			throw new CardAlreadyInDeckException();
			
		} finally {
			em.close();
		}
	
	}
	
}


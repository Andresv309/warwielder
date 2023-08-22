package com.adso.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;

import com.adso.dao.interfaces.UserDAO;
import com.adso.dao.template.AbstractDAOTemplate;
import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.DeckCard;
import com.adso.entities.Pet;
import com.adso.entities.RedemptionCode;
import com.adso.entities.User;
import com.adso.exceptions.decks.CardAlreadyInDeckException;
import com.adso.exceptions.decks.NotValidPositionValue;
import com.adso.exceptions.user.UserAlreadyExistsException;
import com.adso.exceptions.user.UserUnauthorizedForOperationException;
import com.adso.persistence.AppEntityManager;
import com.adso.utils.ExceptionToJsonError;
import com.adso.utils.JsonResponseBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;


public class UserDAOImp extends AbstractDAOTemplate<User, Long> implements UserDAO {

    public UserDAOImp(EntityManagerFactory emf) {
    	super(emf);
    }
    
//	@Override
//	public String getUserInfo(Long id) {
//		EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
//		EntityManager em = emf.createEntityManager();
//		
//		String jsonResponse = null;
//		
//		try {
//			em.getTransaction().begin();
//
//			
//			User userFound = em.find(User.class, id);
//			Set<Deck> userDecks = userFound.getDecks();
//			Set<Deck> userDecksModified = new HashSet<>();
//			
//			for (Deck deckItem: userDecks) {
//				
//				Set<Card> deckCards = deckItem.getCards();
//				Deck modifiedDeck = new Deck();
//				modifiedDeck.setId(deckItem.getId());
//				modifiedDeck.setCards(deckCards);
//
//				userDecksModified.add(modifiedDeck);
//			}
//
//			User newUser = new User();
//			newUser.setId(userFound.getId());
//			newUser.setUsername(userFound.getUsername());
//			newUser.setPet(userFound.getPet());
//			newUser.setDecks(userDecksModified);
//			newUser.setCards(userFound.getCards());
//
//			Gson gson = new Gson();
//			jsonResponse = gson.toJson(newUser);			
//			System.out.println(jsonResponse);
//
//			em.getTransaction().commit();
//		} catch (Exception e) {
//
//        } finally {
//			em.close();
//		}
//		
//		
//		return jsonResponse;
//	}

//	@Override
//	public String getUserDecks(Long userId) {
//		EntityManager em = emf.createEntityManager();
//		
//		String jsonResponse = null;
//		
//		try {
//			
//			em.getTransaction().begin();
//
//			User user = em.find(User.class, userId);
//			Set<Deck> userDecks = user.getDecks();
//			Set<Deck> userDecksModified = new HashSet<>();
//			
//			for (Deck userDeck: userDecks) {
//				Deck deck = new Deck();
//				deck.setId(userDeck.getId());
//				deck.setCards(userDeck.getCards());
//				
//				userDecksModified.add(deck);	
//			}
//			
//			Gson gson = new Gson();
//			jsonResponse = gson.toJson(userDecksModified);			
//			System.out.println(jsonResponse);
//
//			em.getTransaction().commit();
//		} catch (Exception e) {
//
//        } finally {
//			em.close();
//		}
//		
//		
//		return jsonResponse;
//	}

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
//				deckCardMap.put("id", deckCard.getId());
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
		
		

//		List<Deck> userDecks = em.createQuery("SELECT d FROM Deck d WHERE d.user.id = :userId", Deck.class)
//                .setParameter("userId", userId)
//                .getResultList();			
//			
//			
//		for (Deck userDeck : userDecks) {
//
//			List<DeckCard> userDeckCards = em.createQuery("SELECT dc FROM DeckCard dc WHERE dc.deck.id = :deckId", DeckCard.class)
//	                .setParameter("deckId", userDeck.getId())
//	                .getResultList();
//
//			
//			List<Map<String, Object>> cardsList = new ArrayList<>();
//            for (DeckCard deckCard : userDeckCards) {
//                Card card = deckCard.getCard();
//                Map<String, Object> cardMap = new HashMap<>();
//                cardMap.put("id", card.getId());
//                cardMap.put("name", card.getName());
//                cardMap.put("description", card.getDescription());
//                cardMap.put("phrase", card.getPhrase());
//                cardMap.put("skill", card.getSkill());
//                cardMap.put("rarity", card.getRarity());
//                cardMap.put("type", card.getType());
//                cardMap.put("health", card.getHealth());
//                cardMap.put("shield", card.getShield());
//                cardMap.put("attack", card.getAttack());
//                cardMap.put("srcPath", card.getSrcPath());
//                cardMap.put("position", deckCard.getPosition());
//                
//                cardsList.add(cardMap);
//            }
//            
//            Map<String, Object> deckMap = new HashMap<>();
//            deckMap.put("id", userDeck.getId());
//            deckMap.put("deck", cardsList);
//            
//            jsonBuilder.addNestedFieldList("decks", deckMap);
//		}
			
//			em.close();

//		return jsonBuilder.build();
	}
	
	
//	@Override
//	public String getUserDecksCards(Long id) {
//		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
//		EntityManager em = emf.createEntityManager();
//		
////		String jsonResponse = null;
//		
//		try {
//			em.getTransaction().begin();
//			List<Deck> userDecks = em.createQuery("SELECT d FROM Deck d WHERE d.user.id = :userId", Deck.class)
//					.setParameter("userId", id)
//					.getResultList();			
//			
//			List<Map<String, Object>> responseList = new ArrayList<>();
//			
//			for (Deck userDeck : userDecks) {
//				System.out.println("3");
//				List<DeckCard> userDeckCards = em.createQuery("SELECT dc FROM DeckCard dc WHERE dc.deck.id = :deckId", DeckCard.class)
//						.setParameter("deckId", userDeck.getId())
//						.getResultList();
//				
//				System.out.println("4");
//				
//				List<Map<String, Object>> cardsList = new ArrayList<>();
//				for (DeckCard deckCard : userDeckCards) {
//					Card card = deckCard.getCard();
//					Map<String, Object> cardMap = new HashMap<>();
//					cardMap.put("id", card.getId());
//					cardMap.put("name", card.getName());
//					cardMap.put("description", card.getDescription());
//					cardMap.put("phrase", card.getPhrase());
//					cardMap.put("skill", card.getSkill());
//					cardMap.put("rarity", card.getRarity());
//					cardMap.put("type", card.getType());
//					cardMap.put("health", card.getHealth());
//					cardMap.put("shield", card.getShield());
//					cardMap.put("attack", card.getAttack());
//					cardMap.put("srcPath", card.getSrcPath());
//					cardMap.put("position", deckCard.getPosition());
//					
//					cardsList.add(cardMap);
//				}
//				
//				Map<String, Object> deckMap = new HashMap<>();
//				deckMap.put("id", userDeck.getId());
//				deckMap.put("deck", cardsList);
//				responseList.add(deckMap);
//			}
//			
//			
//			
//			
//			Gson gson = new Gson();
//			jsonResponse = gson.toJson(responseList);			
//			System.out.println(jsonResponse);
//			
//			em.getTransaction().commit();
//		} catch (Exception e) {
//			
//		} finally {
//			em.close();
//		}
//		
//		
//		return jsonResponse;
//	}
	
	

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
		
		
		
		
		
//		
//		if (username != null && !username.isBlank() &&
//			password != null && !password.isBlank() &&
//			petId != null
//		) {
//			User newUser = new User();
//			newUser.setUsername(username);
//			newUser.setPassword(password);
//			Pet pet = new Pet();
//			pet.setId(petId);
//			newUser.setPet(pet);
//			
//			System.out.println(jsonObject);
//			System.out.println(newUser);
//			
//			
//			EntityManager em = emf.createEntityManager();
//						
//			try {
//				em.getTransaction().begin();
//
//				em.merge(newUser);
//				
//	            User userFound = em.createQuery("FROM User WHERE username = :username", User.class)
//                .setParameter("username", username)
//                .getSingleResult();
//				
//				userModified.setId(userFound.getId());
//				userModified.setUsername(userFound.getUsername());
//				userModified.setPet(userFound.getPet());
//				
//				em.getTransaction().commit();
//	        } finally {
//				em.close();
//			}
//		}
//		    
//        return userModified;
        
	}
	
//	@Override
//	public User addNewUser(String username, String password) throws ConstraintViolationException {
//		User userModified = new User();
//		
//		// Parse the JSON data using Gson
//		JsonObject jsonObject = JsonParser.parseString(newUserJson).getAsJsonObject();               
//		
//		String username = jsonObject.get("username").getAsString();
//		String password = jsonObject.get("password").getAsString();
//		Long petId = jsonObject.get("petId").getAsLong();
//		
//		if (username != null && !username.isBlank() &&
//				password != null && !password.isBlank() &&
//				petId != null
//				) {
//			User newUser = new User();
//			newUser.setUsername(username);
//			newUser.setPassword(password);
//			Pet pet = new Pet();
//			pet.setId(petId);
//			newUser.setPet(pet);
//			
//			System.out.println(jsonObject);
//			System.out.println(newUser);
//			
//			
//			EntityManager em = emf.createEntityManager();
//			
//			try {
//				em.getTransaction().begin();
//				
//				em.merge(newUser);
//				
//				User userFound = em.createQuery("FROM User WHERE username = :username", User.class)
//						.setParameter("username", username)
//						.getSingleResult();
//				
//				userModified.setId(userFound.getId());
//				userModified.setUsername(userFound.getUsername());
//				userModified.setPet(userFound.getPet());
//				
//				em.getTransaction().commit();
//			} finally {
//				em.close();
//			}
//		}
//		
//		return userModified;
//		
//	}

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
			
			// If exists we update card id
//	        Card card = deckCard.getCard();        
//	        if (cardId != null) {
//	        	Card cardPicked = em.find(Card.class, cardId);
//	        	deckCard.setCard(cardPicked);
//	        	deckCard.getCard().setId(cardId);
//	        }	        
	        
		} catch (NoResultException e) {
			// If doesn't exists we use the information passed by the user.
			deckCard = updateDeckCard;
//			if (cardId != null) {
//				Card cardPicked = em.find(Card.class, cardId);
//				deckCard.setCard(cardPicked);
//			} else {
//				deckCard.setCard(null);
//			}
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
		
		
		
//		try {
//			em.getTransaction().begin();
//
//			DeckCard deckCard = em.createQuery("SELECT dc FROM DeckCard dc WHERE dc.deck.id = :deckId AND dc.position = :position", DeckCard.class)
//		    	.setParameter("deckId", deckId)
//		    	.setParameter("position", position)
//		    	.getSingleResult();
//			
//	        Card card = new Card();
//	        card.setId(cardId);
//			
//			deckCard.setCard(card);
//
//			System.out.println(deckCard);
//			em.merge(updateDeckCard);
//
//			em.getTransaction().commit();
//			
//			return deckCard;
//			
//        } catch (Exception e) {
//        	e.printStackTrace();
////		        responseJson.addProperty("error", "Card already exist in the deck");
//        	throw new UserUnauthorizedForOperationException("Cgaste");
//        	
//        } catch (NoResultException e) {
//        	em.persist(updateDeckCard);
//        	em.getTransaction().commit();
//        	return newDeckCard;
//        	
//        } finally {
//			em.close();
//		}
			

		
	}
	
	
//	@Override
//	public String updateDeck(String updateDeckCardJson, Long userId) {
//		System.out.println("userId: " + userId);
//		
//		String jsonResponse = null;
//		DeckCard deckCard = new DeckCard();
//		EntityManager em = emf.createEntityManager();
//		
//		// Parse the JSON data using Gson
//		JsonObject jsonObject = JsonParser.parseString(updateDeckCardJson).getAsJsonObject();
//		
//		// Required parameters
//		Long deckId = null;
//		Integer position = null;
//		
//		// Optional parameters
//		Long id = null;
//		Long cardId = null;
//		
//		if (!jsonObject.has("position") || !jsonObject.has("deckId")) {
//			// Create a response JSON indicating missing fields
//			JsonObject responseJson = new JsonObject();
//			responseJson.addProperty("error", "Missing required fields: position and/or deckId");
//			
//			jsonResponse = responseJson.toString();
//			return jsonResponse;
//			
//		}
//		
//		// Set required parameters
//		deckId = jsonObject.get("deckId").getAsLong();
//		position = jsonObject.get("position").getAsInt();
//		
//		
//		if (!(1 <= position && position <=8)) {
//			// Create a response JSON indicating missing fields
//			JsonObject responseJson = new JsonObject();
//			responseJson.addProperty("error", "Invalid card position");
//			
//			jsonResponse = responseJson.toString();
//			return jsonResponse;
//		}
//		
//		Deck deck = new Deck();
//		deck.setId(deckId);
//		
//		deckCard.setDeck(deck);
//		deckCard.setPosition(position);
//		
//		
//		
//		// Verify the user is authorized to make the change
//		Deck askedDeck = em.find(Deck.class, deckId);
//		Long askedUserId = askedDeck.getUser().getId();
//		if (askedUserId != userId) {
//			// Create a response JSON indicating user not authorized
//			JsonObject responseJson = new JsonObject();
//			responseJson.addProperty("error", "User is not authorized for this operation");
//			
//			jsonResponse = responseJson.toString();
//			return jsonResponse;
//		}
//		
//		// Verify the user owns the card if passed
//		Set<Card> userCards = askedDeck.getUser().getCards();
//		if (jsonObject.has("cardId")) {
//			cardId = jsonObject.get("cardId").getAsLong();
//			boolean userOwnsCard = false;
//			
//			for (Card userCard : userCards) {
//				if (userCard.getId().equals(cardId)) {
//					userOwnsCard = true;
//					break;
//				}
//			}
//			
//			if (!userOwnsCard) {
//				// Create a response JSON indicating user doesn't own the card
//				JsonObject responseJson = new JsonObject();
//				responseJson.addProperty("error", "User does not own the specified card");
//				
//				jsonResponse = responseJson.toString();
//				return jsonResponse;
//			} else {
//				Card card = new Card();
//				card.setId(cardId);
//				deckCard.setCard(card);
//			}
//		}
//		
//		
//		// Set optional parameter if passed
//		if (jsonObject.has("id")) {
//			id = jsonObject.get("id").getAsLong();
//			deckCard.setId(id);
//		}
//		
//		
//		try {
//			em.getTransaction().begin();
//			
//			em.merge(deckCard);
//			
//			Gson gson = new Gson();
//			jsonResponse = gson.toJson(deckCard);	
//			
//			em.getTransaction().commit();
//			
//		} catch (Exception e) {
//			
////        	int errorCode = e.getErrorCode();
////        	System.out.println(errorCode);
//			
////        	if (errorCode == 1062 || errorCode == 23000) {
//			// Create a response JSON indicating card trying to assign is not valid
//			JsonObject responseJson = new JsonObject();
//			responseJson.addProperty("error", "Card already exist in the deck");
//			
//			jsonResponse = responseJson.toString();
//			return jsonResponse;
////        	}
//			
//			
//			
//			
//		} finally {
//			
//			em.close();
//		}
//		
//		System.out.println(jsonResponse);
//		return jsonResponse;
//	}


	
	
	
	
	
	
	@Override
	public String redeemCardCode(String redeemCodeJson, Long userId) {
		String jsonResponse = null;
		DeckCard deckCard = new DeckCard();
		EntityManager em = emf.createEntityManager();
		
		
		try {
			em.getTransaction().begin();

			em.merge(deckCard);
			
			Gson gson = new Gson();
			jsonResponse = gson.toJson(deckCard);	
			
			em.getTransaction().commit();
	    } finally {
	    	  
			em.close();
		}
			
		System.out.println(jsonResponse);
		return jsonResponse;
		
		
	}



}


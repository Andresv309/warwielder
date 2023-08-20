package com.adso.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;

import com.adso.dao.interfaces.UserDAO;
import com.adso.dao.template.AbstractDAOTemplate;
import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.DeckCard;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.persistence.AppEntityManager;
import com.adso.utils.ExceptionToJsonError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


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
	public String getUserUnlockedCards(Long id) {
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			em.getTransaction().begin();

			User user = em.find(User.class, id);
			Set<Card> unlockedCards = user.getCards();
			
			Gson gson = new Gson();
			jsonResponse = gson.toJson(unlockedCards);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}
	
	@Override
	public String getUserDecksCards(Long id) {
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			em.getTransaction().begin();
			List<Deck> userDecks = em.createQuery("SELECT d FROM Deck d WHERE d.user.id = :userId", Deck.class)
	                .setParameter("userId", id)
	                .getResultList();			
			
			List<Map<String, Object>> responseList = new ArrayList<>();
			
			for (Deck userDeck : userDecks) {
				System.out.println("3");
				List<DeckCard> userDeckCards = em.createQuery("SELECT dc FROM DeckCard dc WHERE dc.deck.id = :deckId", DeckCard.class)
		                .setParameter("deckId", userDeck.getId())
		                .getResultList();
				
				System.out.println("4");
				
				List<Map<String, Object>> cardsList = new ArrayList<>();
	            for (DeckCard deckCard : userDeckCards) {
	                Card card = deckCard.getCard();
	                Map<String, Object> cardMap = new HashMap<>();
	                cardMap.put("id", card.getId());
	                cardMap.put("name", card.getName());
	                cardMap.put("description", card.getDescription());
	                cardMap.put("phrase", card.getPhrase());
	                cardMap.put("skill", card.getSkill());
	                cardMap.put("rarity", card.getRarity());
	                cardMap.put("type", card.getType());
	                cardMap.put("health", card.getHealth());
	                cardMap.put("shield", card.getShield());
	                cardMap.put("attack", card.getAttack());
	                cardMap.put("srcPath", card.getSrcPath());
	                cardMap.put("position", deckCard.getPosition());
	                
	                cardsList.add(cardMap);
	            }
	            
	            Map<String, Object> deckMap = new HashMap<>();
	            deckMap.put("id", userDeck.getId());
	            deckMap.put("deck", cardsList);
	            responseList.add(deckMap);
			}
			
			
			
			
			Gson gson = new Gson();
			jsonResponse = gson.toJson(responseList);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}
	
	

	@Override
	public User addNewUser(String newUserJson) throws ConstraintViolationException {
		User userModified = new User();
		
        // Parse the JSON data using Gson
        JsonObject jsonObject = JsonParser.parseString(newUserJson).getAsJsonObject();               
       
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();
		Long petId = jsonObject.get("petId").getAsLong();
		
		if (username != null && !username.isBlank() &&
			password != null && !password.isBlank() &&
			petId != null
		) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			Pet pet = new Pet();
			pet.setId(petId);
			newUser.setPet(pet);
			
			System.out.println(jsonObject);
			System.out.println(newUser);
			
			
			EntityManager em = emf.createEntityManager();
						
			try {
				em.getTransaction().begin();

				em.merge(newUser);
				
	            User userFound = em.createQuery("FROM User WHERE username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
				
				userModified.setId(userFound.getId());
				userModified.setUsername(userFound.getUsername());
				userModified.setPet(userFound.getPet());
				
				em.getTransaction().commit();
	        } finally {
				em.close();
			}
		}
		    
        return userModified;
        
	}

	@Override
	public String updateDeck(String updateDeckCardJson, Long userId) {
		String jsonResponse = null;
		
        // Parse the JSON data using Gson
        JsonObject jsonObject = JsonParser.parseString(updateDeckCardJson).getAsJsonObject();               
       
        System.out.println("Evaluando Json");
        
        if (!jsonObject.has("position") || !jsonObject.has("deckId")) {
            // Create a response JSON indicating missing fields
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("error", "Missing required fields: position and/or deckId");
            
            jsonResponse = responseJson.toString();
            return jsonResponse;
        }
        
        
        Long deckId = jsonObject.get("deckId").getAsLong();
        Integer position = jsonObject.get("position").getAsInt();
        
		System.out.println("Json Correcto");
		
		EntityManager em = emf.createEntityManager();
		
		try {
			System.out.println("Comenzando transaccion");
			em.getTransaction().begin();
	
			DeckCard deckCard = new DeckCard();
			
	        Long cardId = null;
	        Card card = null;
	        if (jsonObject.has("cardId")) {
	        	cardId = jsonObject.get("cardId").getAsLong();
//	        	card = em.find(Card.class, cardId);  
	        	card = new Card();
	        	card.setId(cardId);
	        	deckCard.setCard(card);
	        }
			
//	        Deck deck = em.find(Deck.class, deckId);	
        	Deck deck = new Deck();
        	deck.setId(deckId);
        	deckCard.setDeck(deck);
			
	        Long id = null;
	        if (jsonObject.has("id")) {
	            id = jsonObject.get("id").getAsLong();
	            deckCard.setId(id);
	        }
			
			deckCard.setPosition(position);

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


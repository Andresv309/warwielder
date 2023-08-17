package com.adso.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;

import com.adso.dao.interfaces.UserDAO;
import com.adso.dao.template.AbstractDAOTemplate;
import com.adso.entities.Card;
import com.adso.entities.Deck;
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
    
	@Override
	public String getUserInfo(Long id) {
		EntityManagerFactory emf = AppEntityManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			em.getTransaction().begin();

			
			User userFound = em.find(User.class, id);
			Set<Deck> userDecks = userFound.getDecks();
			Set<Deck> userDecksModified = new HashSet<>();
			
			for (Deck deckItem: userDecks) {
				
				Set<Card> deckCards = deckItem.getCards();
				Deck modifiedDeck = new Deck();
				modifiedDeck.setId(deckItem.getId());
				modifiedDeck.setCards(deckCards);

				userDecksModified.add(modifiedDeck);
			}

			User newUser = new User();
			newUser.setId(userFound.getId());
			newUser.setUsername(userFound.getUsername());
			newUser.setPet(userFound.getPet());
			newUser.setDecks(userDecksModified);
			newUser.setCards(userFound.getCards());

			Gson gson = new Gson();
			jsonResponse = gson.toJson(newUser);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}

	@Override
	public String getUserDecks(Long userId) {
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			
			em.getTransaction().begin();

			User user = em.find(User.class, userId);
			Set<Deck> userDecks = user.getDecks();
			Set<Deck> userDecksModified = new HashSet<>();
			
			for (Deck userDeck: userDecks) {
				Deck deck = new Deck();
				deck.setId(userDeck.getId());
				deck.setCards(userDeck.getCards());
				
				userDecksModified.add(deck);	
			}
			
			Gson gson = new Gson();
			jsonResponse = gson.toJson(userDecksModified);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}

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
	public String updateDeck(Long deckId) {
		// TODO Auto-generated method stub
		return null;
	}



	
//	@Override
//	public String addNewUser(String newUserJson) throws ConstraintViolationException {
//		
//		String jsonResponse = null;
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
//			User userModified = new User();
//			
//			try {
//				em.getTransaction().begin();
//				
//				em.merge(newUser);
////				em.refresh(newUser);
//				
//				User userFound = em.createQuery("FROM User WHERE username = :username", User.class)
//						.setParameter("username", username)
//						.getSingleResult();
//				
//				
//				userModified.setId(userFound.getId());
//				userModified.setUsername(userFound.getUsername());
//				userModified.setPet(userFound.getPet());
//				
//				
//				Gson gson = new Gson();
//				jsonResponse = gson.toJson(userModified);		
//				System.out.println(jsonResponse);
//				
//				em.getTransaction().commit();
//			} finally {
//				em.close();
//			}
//		}
//		
//		
//		return jsonResponse;
//		
//	}
    
    
    
    
    
	
//	@Override
//	public User getById(Long idRecord) {
////		final Wrapper<User> userWrapper = new Wrapper<>();   
////
////	    performTransaction(em -> {
//////	        User user = em.createQuery("FROM User WHERE id = :id", User.class)
//////	                .setParameter("id", idRecord)
//////	                .getSingleResult();
////	        User user = em.find(User.class, idRecord);
////	        userWrapper.set(user);
////	    });
////	    
////	    System.out.println(userWrapper.get());
////	    
////	    return userWrapper.get();
//		
//	
//		User user = null;
//
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//
//            user = em.find(User.class, idRecord);
//
//            tx.commit();
//        } catch (Exception e) {
//
//        } finally {
//            em.close();
//        }
//		
//		return user;
//		
//	}
//
//	@Override
//	public List<User> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}


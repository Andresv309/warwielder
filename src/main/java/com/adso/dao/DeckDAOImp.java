package com.adso.dao;

import java.util.HashSet;
import java.util.Set;

import com.adso.dao.interfaces.DeckDAO;
import com.adso.dao.template.AbstractDAOTemplate;
import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.User;
import com.adso.persistence.AppEntityManager;
import com.google.gson.Gson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DeckDAOImp extends AbstractDAOTemplate<Deck, Long> implements DeckDAO {

    public DeckDAOImp(EntityManagerFactory emf) {
    	super(emf);
    }
	
	@Override
	public String getDecksForUser(Long id) {		
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

}

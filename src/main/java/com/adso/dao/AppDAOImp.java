package com.adso.dao;

import java.util.List;

import com.adso.dao.interfaces.AppDAO;
import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.exceptions.cards.NotFoundCardException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AppDAOImp implements AppDAO {
	
    private EntityManagerFactory emf;

    public AppDAOImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

	@Override
	public List<Card> getAppCards() {
		EntityManager em = emf.createEntityManager();		
		List<Card> appCards = em.createQuery("FROM Card", Card.class).getResultList();
		em.close();

		return appCards;
	}

	@Override
	public List<Pet> getAppPets() {
		EntityManager em = emf.createEntityManager();		
		List<Pet> appPets = em.createQuery("FROM Pet", Pet.class).getResultList();
		em.close();

		return appPets;	
	}

	@Override
	public Card getAppCard(Long id) throws NotFoundCardException {
		EntityManager em = emf.createEntityManager();
		Card appCard = em.find(Card.class, id);
		em.close();
		
		if (appCard == null) {
			throw new NotFoundCardException(id);
		}
		return appCard;
	}
   
    
}

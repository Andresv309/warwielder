package com.adso.dao;

import java.util.List;

import com.adso.dao.interfaces.AppDAO;
import com.adso.entities.Card;
import com.adso.entities.Pet;

import com.google.gson.Gson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AppDAOImp implements AppDAO {
	
    private EntityManagerFactory emf;

    public AppDAOImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

	@Override
	public String getAppCards() {
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			em.getTransaction().begin();

			List<Card> appCards = em.createQuery("FROM Card", Card.class).getResultList();

			Gson gson = new Gson();
			jsonResponse = gson.toJson(appCards);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}

	@Override
	public String getAppPets() {
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			em.getTransaction().begin();

			List<Pet> appCards = em.createQuery("FROM Pet", Pet.class).getResultList();

			Gson gson = new Gson();
			jsonResponse = gson.toJson(appCards);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}

	@Override
	public String getAppCard(Long id) {
		EntityManager em = emf.createEntityManager();
		
		String jsonResponse = null;
		
		try {
			em.getTransaction().begin();

			Card appCard = em.find(Card.class, id);

			Gson gson = new Gson();
			jsonResponse = gson.toJson(appCard);			
			System.out.println(jsonResponse);

			em.getTransaction().commit();
		} catch (Exception e) {

        } finally {
			em.close();
		}
		
		
		return jsonResponse;
	}
   
    
}

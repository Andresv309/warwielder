package com.adso.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adso.dao.interfaces.AppDAO;
import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.enums.Race;
import com.adso.enums.Rarity;
import com.adso.exceptions.cards.NotFoundCardException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AppDAOImp implements AppDAO {
	
    private EntityManagerFactory emf;

    public AppDAOImp(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    public Long getTotalNumberOfCards() {
    	EntityManager em = emf.createEntityManager();
    	
    	TypedQuery<Long> countQuery  = em.createQuery("SELECT COUNT(c) FROM Card c", Long.class);
    	Long totalCount = countQuery.getSingleResult();
  
    	em.close();
    	
    	return totalCount;
    }
    
    
    
    private Predicate[] buildPredicates(Map<String, Object> queryParams, CriteriaBuilder cb, Root<Card> root) {
        List<Predicate> predicates = new ArrayList<>();

        // Add your filtering conditions based on query parameters here
        Rarity rarity = (Rarity) queryParams.get("rarity");
        if (rarity != null) {
            predicates.add(cb.equal(root.get("rarity"), rarity));
        }
        
        Race race = (Race) queryParams.get("race");
        if (race != null) {
            predicates.add(cb.equal(root.get("race"), race));
        }

        return predicates.toArray(new Predicate[0]);
    }

	@Override
	public List<Card> getAppCards(Map<String, Object> queryParams) {
		EntityManager em = emf.createEntityManager();	
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Card> query = cb.createQuery(Card.class);
	    Root<Card> root = query.from(Card.class);
	    
	    Predicate[] predicates = buildPredicates(queryParams, cb, root);

	    query.select(root)
	         .where(predicates);

	    TypedQuery<Card> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int) queryParams.get("offset"));
        typedQuery.setMaxResults((int) queryParams.get("limit"));

	    List<Card> appCards = typedQuery.getResultList();

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


package com.adso.dao;

import java.util.List;

import com.adso.dao.interfaces.DeckDAO;
import com.adso.dao.interfaces.UserDAO;
import com.adso.dao.template.AbstractDAOTemplate;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.enums.Rarity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class UserDAOImp extends AbstractDAOTemplate<User, Long> implements UserDAO {

    public UserDAOImp(EntityManagerFactory emf) {
    	super(emf);
    }
	
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


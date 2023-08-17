package com.adso.dao.template;

import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public abstract class AbstractDAOTemplate<T, U> {
	
    protected EntityManagerFactory emf;

    public AbstractDAOTemplate(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public void performTransaction(Consumer<EntityManager> transactionCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            transactionCode.accept(em);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
	}

	public void save(T record) {
	    performTransaction(em -> {
	        em.merge(record);
	    });
	};
	
	public void remove(T record) {
	    performTransaction(em -> {
	    	em.remove(em.contains(record) ? record : em.merge(record));
	    });
	};
	
	public void undo(T record) {
		performTransaction(em -> {
			em.refresh(record);
		});
	};
	
   
//    public void performTransaction(Consumer<EntityManager> transactionCode) {
//    	EntityManager em = emf.createEntityManager();
//    	try {
//    		em.getTransaction().begin();
//    		
//    		transactionCode.accept(em);
//    		
//    		em.getTransaction().commit();
//    	} finally {
//    		em.close();
//    	}
//    }

//    public void save(T record) {
//        performTransaction(em -> {
//            em.persist(record);
//        });
//    };
//    
//    public void remove(T record) {
//        performTransaction(em -> {
//            em.remove(record);
//        });
//    };
//    
//    public void undo(T record) {
//    	performTransaction(em -> {
//    		em.refresh(record);
//    	});
//    };
    
    
//    public void save(T record) {
//        performTransaction((EntityManager em) -> {
//            em.persist(record);
//            return null;
//        });
//    }
//
//    public void remove(T record) {
//        performTransaction((EntityManager em) -> {
//            em.remove(em.contains(record) ? record : em.merge(record));
//            return null;
//        });
//    }
//
//    public void undo(T record) {
//        performTransaction((EntityManager em) -> {
//            em.refresh(record);
//            return null;
//        });
//    }

//    public <R> R performTransaction(Function<EntityManager, R> transactionCode) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//
//            R result = transactionCode.apply(em);
//
//            tx.commit();
//            return result;
//        } catch (Exception e) {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            throw e;
//        } finally {
//            em.close();
//        }
//    }

       
}

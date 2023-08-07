package com.adso.persistence;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public final class AppEntityManager {
	
	private static AppEntityManager instance;
	private static EntityManager entityManager;
	
	public AppEntityManager() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");		
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(
						new CustomPersistenceUnitInfo(),
						props
				);
		
		entityManager = emf.createEntityManager();
	}
	
	public static AppEntityManager getInstance() {
        if (instance == null) {
        	instance = new AppEntityManager();
        }
        return instance;
	}
	
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public static void closeEntityManager() {
    	entityManager.close();
    }
	
}

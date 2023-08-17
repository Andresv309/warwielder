package com.adso.persistence;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.EntityManagerFactory;

public final class AppEntityManager {
	
	private static AppEntityManager instance;
	private static EntityManagerFactory emf;
	private static CustomPersistenceUnitInfo cpui;
	
	public AppEntityManager() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		
		cpui = new CustomPersistenceUnitInfo();
		
		emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(
						cpui,
						props
				);
	}
	
	public static AppEntityManager getInstance() {
        if (instance == null) {
        	instance = new AppEntityManager();
        	System.out.println("Hibernate Manager Initilized");
        }
        return instance;
	}
	
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("Hibernate Manager Closed!!");
        }
        
        if (cpui != null && !cpui.isClosed()) {
        	cpui.close();
        	System.out.println("Hikary Datasource Closed!!");
        }
    }
	
}

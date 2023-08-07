package com.adso.context;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.adso.persistence.CustomPersistenceUnitInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UserContext {
	
	Map<String, Object> userContext = new HashMap<>();
	EntityManagerFactory emf = null;
	EntityManager em = null;
	
	public UserContext () {
		createContext();
	}
	
	private void createContext() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
		
		this.emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(
						new CustomPersistenceUnitInfo(),
						props
				);
		
		this.em = emf.createEntityManager();
	}	
	
	public Map<String, Object> getUserContext() {
		return userContext;
	}
}
















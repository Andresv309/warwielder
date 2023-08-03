package com.adso;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.adso.entities.Employee;
import com.adso.persistence.CustomPersistenceUnitInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
		
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(
						new CustomPersistenceUnitInfo(),
						props
				);
		
		EntityManager em = emf.createEntityManager();
		
		try {
	
			em.getTransaction().begin();
			
//			Save data to DB			
			Employee employee = new Employee("Carl", "Sagan", LocalDate.now());			
			em.persist(employee);
			
//			Select data form DB
//			Employee e1 = em.find(Employee.class, 1);
//			System.out.println(e1);
			
//			Edit data form DB
//			Employee e2 = em.find(Employee.class, 2);
//			e2.setName("Pepe");
//			System.out.println(e2);
			
			
//			em.persist();	-> Adding an entity to the context.
//			em.find();		-> Finds by PK. Get from DB and add it to the context if it doesn't already exist.
//			em.remove();	-> Marking entity for removal.
//			em.merge();		-> Merges an entity from outside the context to the context.
//			em.refresh();	-> Mirror the context from the database. (Like an undo operation).
//			em.detach();	-> Taking the entity out of the context.
//			em.getReference();	-> Creates a "shell" for the element and only queries it when it is required in the application.
			
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}
}

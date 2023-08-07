package com.adso;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.persistence.CustomPersistenceUnitInfo;
//import com.adso.utils.JwtGenerator;
//import com.adso.utils.JwtValidator;
//import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class Main {
	
//	private static String token;
	
	public static void main(String[] args) {
//		seedDB();

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");	
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
		.createContainerEntityManagerFactory(
				new CustomPersistenceUnitInfo(),
				props
		);
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			User user = em.find(User.class, 1);
			Set<Deck> userDecks = user.getDecks();
			
			for (Deck deckItem: userDecks) {
				Set<Card> deckCards = deckItem.getCards();
				for (Card cardItem: deckCards) {
					System.out.println(cardItem);
				}
				System.out.println("\n---------------------------\n");
			}
		
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		
		
		
		
//		try {
//			JwtGenerator generator = new JwtGenerator();
//			
//			Map<String, String> claims = new HashMap<>();
//			
//			claims.put("uuid", "123456798");
//			claims.put("email", "estoesunaprueba@gmail.com");
//			claims.put("role", "USER");
//			
//			token = generator.generateJwt(claims);
//			System.out.println(token);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			
//			final JwtValidator validator = new JwtValidator();
//			
//			DecodedJWT decodedToken = validator.validate(token);
//			System.out.println("Jwt is valid");
//			System.out.println(decodedToken);
//			
//		} catch (InvalidParameterException e) {
//			System.out.println("Jwt is invalid");
//			e.printStackTrace();
//		}
		
		
		
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		
//		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.show_sql", "true");
//		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
//		
//		
//		EntityManagerFactory emf = new HibernatePersistenceProvider()
//				.createContainerEntityManagerFactory(
//						new CustomPersistenceUnitInfo(),
//						props
//				);
//		
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//	
//			em.getTransaction().begin();
//			
////			Save data to DB			
//			User user = new User("Carl", "123456");			
//			em.persist(user);
//			
//			
//			
////			Save data to DB			
////			Employee employee = new Employee("Carl", "Sagan", LocalDate.now());			
////			em.persist(employee);
//			
////			Select data form DB
////			Employee e1 = em.find(Employee.class, 1);
////			System.out.println(e1);
//			
////			Edit data form DB
////			Employee e2 = em.find(Employee.class, 2);
////			e2.setName("Pepe");
////			System.out.println(e2);
//			
//			
////			em.persist();	-> Adding an entity to the context.
////			em.find();		-> Finds by PK. Get from DB and add it to the context if it doesn't already exist.
////			em.remove();	-> Marking entity for removal.
////			em.merge();		-> Merges an entity from outside the context to the context.
////			em.refresh();	-> Mirror the context from the database. (Like an undo operation).
////			em.detach();	-> Taking the entity out of the context.
////			em.getReference();	-> Creates a "shell" for the element and only queries it when it is required in the application.
//			
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
		
	}
	
	private static void seedDB() {
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
			
			Pet pet = new Pet("Perro", "Velocidad", 2, Rarity.COMMON);
			User user = new User("Carl", "123456", pet);
			Card card1 = new Card(
					"Charles",
					"A la victoria",
					"Porta un martillo",
					"Warrior",
					Rarity.RARE,
					"Golpe Fuerte",
					85,
					59,
					148
			);
			Card card2 = new Card(
					"Merlin",
					"Books for the win",
					"Has a Castle",
					"Mage",
					Rarity.EPIC,
					"Frezzing Ice",
					85,
					59,
					148
			);
			Card card3 = new Card(
					"Thor",
					"Lives in azargth",
					"Has a Hammer",
					"Nordic",
					Rarity.ADVANCED,
					"Launches Hammer",
					85,
					59,
					148
			);
			
			Set<Card> cards1 = new HashSet<>();
			cards1.add(card1);
			cards1.add(card2);
			
			Set<Card> cards2 = new HashSet<>();
			cards2.add(card2);
			cards2.add(card3);
			
			Deck deck1 = new Deck(user,cards1);
			Deck deck2 = new Deck(user,cards2);
			
//			em.persist(pet);
			em.persist(user);
//			em.persist(card1);
//			em.persist(card2);
			em.persist(deck1);
			em.persist(deck2);
		
			
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	
	
}

package com.adso;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.DeckCard;
import com.adso.entities.Pet;
import com.adso.entities.RedemptionCode;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.persistence.CustomPersistenceUnitInfo;
import com.adso.utils.PasswordHashing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class Main {

	public static void main(String[] args) {
//		createDB();
		
		generateCards();
//		seedDB();
//		createDeckCards();
//		createUserCards();

	}
	
	
	private static void createDB() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create");
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
		.createContainerEntityManagerFactory(
				new CustomPersistenceUnitInfo(),
				props
		);
		
		EntityManager em = emf.createEntityManager();
	}
	
	
	private static void seedDB() {
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
			Pet pet = new Pet("Perro", "Velocidad", 2, Rarity.COMMON, "");
			
			
			User user = new User("Carl", PasswordHashing.hashPassword("123456"));
			user.setSelectedPet(pet);
//			Card card1 = new Card(
//					"Charles",
//					"A la victoria",
//					"Porta un martillo",
//					"Warrior",
//					Rarity.RARE,
//					"Golpe Fuerte",
//					85,
//					59,
//					148,
//					""
//					);
//			Card card2 = new Card(
//					"Merlin",
//					"Books for the win",
//					"Has a Castle",
//					"Mage",
//					Rarity.EPIC,
//					"Frezzing Ice",
//					85,
//					59,
//					148,
//					""
//					);
//			Card card3 = new Card(
//					"Thor",
//					"Lives in azargth",
//					"Has a Hammer",
//					"Nordic",
//					Rarity.ADVANCED,
//					"Launches Hammer",
//					85,
//					59,
//					148,
//					""
//					);
			
//			
//			em.persist(card1);
//			em.persist(card2);
//			em.persist(card3);
			
			em.persist(pet);
			em.persist(user);

			
			Pet pet1 = new Pet("Gato", "Agilidad", 3, Rarity.COMMON, "");
			User user1 = new User("Carlos", PasswordHashing.hashPassword("123456"));
			user1.setSelectedPet(pet1);
			
			Pet pet2 = new Pet("Conejo", "Salto", 1, Rarity.COMMON, "");
			User user2 = new User("Anna", PasswordHashing.hashPassword("123456"));
			user2.setSelectedPet(pet2);
			em.persist(pet1);
			em.persist(user1);
			em.persist(pet2);
			em.persist(user2);
			
	
			RedemptionCode redemptionCode1 = new RedemptionCode("12");
			RedemptionCode redemptionCode2 = new RedemptionCode("34");
			RedemptionCode redemptionCode3 = new RedemptionCode("56");
			RedemptionCode redemptionCode4 = new RedemptionCode("78");
			RedemptionCode redemptionCode5 = new RedemptionCode("90");

			em.persist(redemptionCode1);
			em.persist(redemptionCode2);
			em.persist(redemptionCode3);
			em.persist(redemptionCode4);
			em.persist(redemptionCode5);

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	private static void createDeckCards() {
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
			
			Deck deck1 = em.find(Deck.class, 1);
			Deck deck2 = em.find(Deck.class, 2);
			
			Card card1 = em.find(Card.class, 1);
			Card card2 = em.find(Card.class, 2);
			Card card3 = em.find(Card.class, 3);
			
			DeckCard deckCard1 = new DeckCard(deck1, card1, 1);
			DeckCard deckCard2 = new DeckCard(deck1, card2, 2);
			DeckCard deckCard3 = new DeckCard(deck1, card3, 6);
			DeckCard deckCard4 = new DeckCard(deck2, card3, 1);
			DeckCard deckCard5 = new DeckCard(deck2, card1, 4);
			
			em.persist(deckCard1);
			em.persist(deckCard2);
			em.persist(deckCard3);
			em.persist(deckCard4);
			em.persist(deckCard5);
			

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	private static void createUserCards() {
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
			
			Card card1 = em.find(Card.class, 1);
			Card card2 = em.find(Card.class, 2);
			Card card3 = em.find(Card.class, 3);
			
			Set<Card> cards = new HashSet<>();
			cards.add(card1);
			cards.add(card2);
			cards.add(card3);
			
			user.setCards(cards);
			
			
			em.persist(user);
	
	
			

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	private static void generateCards () {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(
						new CustomPersistenceUnitInfo(),
						props
						);
		
		 try (EntityManager em = emf.createEntityManager()) {
	            // Read JSON data and parse into Card objects using Gson
	            Gson gson = new Gson();
	            InputStream jsonStream = Main.class.getResourceAsStream("/com/adso/appCards.json");
	            Reader reader = new InputStreamReader(jsonStream);
	            List<Card> cards = gson.fromJson(reader, new TypeToken<List<Card>>() {}.getType());

	            // Save cards using Hibernate
	            try {
	            	em.getTransaction().begin();
	    
	                for (Card card : cards) {
	                	
	                	Card newCard = new Card(
	                			card.getCode(),
	                			card.getName(),
	                			card.getDescription(),
	                			card.getRarity(),
	                			card.getRace(),
	                			card.getHealth(),
	                			card.getCost(),
	                			card.getAttack(),
	                			card.getImg()
            			);
	                	
	                	
	                    em.persist(newCard);
	                }
	                
	                em.getTransaction().commit();
	                
	                
	            } catch (Exception e) {
	            	em.getTransaction().rollback();
	                e.printStackTrace();
	            } finally {
	    			em.close();
	    		}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	
	
	
	
}


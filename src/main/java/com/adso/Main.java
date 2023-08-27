package com.adso;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
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
import com.adso.utils.CuidGenerator;
import com.adso.utils.PasswordHashing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class Main {

	public static void main(String[] args) {
//		createDB();
//		generateCards();
		
		seedDB();
		createDeckCards();
		createUserCards();

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
			Pet pet1 = new Pet("Rocket", "<b>Resistencia Mágica:</b> El portador recibe <b>-2</b> de daño de hechizos y habilidades enemigas.", Rarity.FREE, "a5ed9a63-8530-4a6a-b103-5f34e890a8e8.png");
			Pet pet2 = new Pet("Frostbloom", "<b>Reflejo Frío:</b> Cuando el portador recibe daño, tiene <b>20%</b> de probabilidad de reflejar <b>2</b> de daño al atacante.", Rarity.FREE, "3098669a-12a3-43d3-bf8c-e117f2385481.png");
			Pet pet3 = new Pet("Hashiko", "<b>Refuerzo Místico:</b> Al portador se le otorga <b>+4</b> de vida y <b>+1</b> de daño.", Rarity.FREE, "ee5c4c5c-7f7c-4e86-af6d-474ad483fada.png");
			Pet pet4 = new Pet("Ragnar", "<b>Furia Desatada:</b> Cuando el portador recibe daño, su daño aumenta en <b>+2</b> en su próximo turno.", Rarity.FREE, "c07a315c-633c-4de7-a2fe-3565d2028360.png");
			Pet pet5 = new Pet("Metacho", "<b>Curación Natural:</b> El portador recupera <b>+1</b> de vida al final de cada turno.", Rarity.COMMON, "075ae3f5-ec6c-4986-892b-e68612ae7a50.png");
			Pet pet6 = new Pet("Stardustfury", "<b>Ferocidad Felina:</b> El portador inflige <b>+2</b> de daño adicional en cada ataque.", Rarity.COMMON, "cb668d72-6f36-4602-85d5-835d23556141.png");
			Pet pet7 = new Pet("Thalia", "<b>Hambriento de Batalla:</b> Por cada carta derrotado por el portador, su vida máxima aumenta en <b>+1</b>.", Rarity.COMMON, "0e8027fa-8430-47cf-bd7b-e1ff37689d9e.png");
			Pet pet8 = new Pet("Spike", "<b>Espinas Enfurecidas:</b> Cuando el portador recibe daño, sus espinas se erizan y daña al atacante por <b>3</b> de daño.", Rarity.RARE, "17f3d5aa-97a1-4159-af3a-36579ecd957c.png");
			Pet pet9 = new Pet("Zephyrgrin", "<b>Viento Veloz:</b> El portador tiene <b>30%</b> de probabilidad de esquivar los ataques enemigos.", Rarity.RARE, "1ffe6db8-cb2b-41a9-af69-5ec4d9615bb7.png");
			Pet pet10 = new Pet("Titanclaw", "<b>Ataque Salvaje:</b> Aumenta el daño del portador en <b>+2</b> por cada <b>10</b> de vida que le falte.", Rarity.EPIC, "fda7d648-46d4-4797-aa6f-2ce9282d1b24.png");
			Pet pet11 = new Pet("SerpentSpark", "<b>Regalo Divino:</b> Al comienzo de cada turno, el portador tiene <b>50%</b> de probabilidad de recuperar <b>2</b> de vida o de infligir <b>2</b> de daño a un enemigo aleatorio.", Rarity.EPIC, "146c3d1e-e4be-488e-ac8f-7089c8a7d135.png");
			Pet pet12 = new Pet("EmberBite", "<b>Evasión Ágil:</b> El portador tiene <b>50%</b> de probabilidad de esquivar ataques enemigos y recibir daño reducido a la mitad.", Rarity.LEGENDARY, "fbf52f9b-84ac-4638-a9a5-330929c146b2.png");
			
		
		    List<Pet> pets = Arrays.asList(pet1, pet2, pet3, pet4, pet5, pet6, pet7, pet8, pet9, pet10, pet11, pet12);

		    for (Pet pet : pets) {
		        em.persist(pet);
		    }

			User user = new User("Carl", PasswordHashing.hashPassword("123456"));
			user.setSelectedPet(pet1);		
			user.setCoins(10000);

			User user1 = new User("Carlos", PasswordHashing.hashPassword("123456"));
			user1.setSelectedPet(pet2);
			user1.setCoins(10000);
			
			User user2 = new User("Anna", PasswordHashing.hashPassword("123456"));
			user2.setSelectedPet(pet3);
			user2.setCoins(10000);
		
			em.persist(user);
			em.persist(user1);
			em.persist(user2);
			
			
			for (int i = 0; i < 100; i++) {
				em.persist(new RedemptionCode(CuidGenerator.generate()));
			}
			

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
	                	System.out.println("Current ---->>" + card.getCode());
	                	
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


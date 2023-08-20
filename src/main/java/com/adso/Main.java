package com.adso;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.DeckCard;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.enums.Rarity;
import com.adso.persistence.CustomPersistenceUnitInfo;
//import com.adso.utils.JwtGenerator;
//import com.adso.utils.JwtValidator;
//import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class Main {
	
//	private static String token;
	
	public static void main(String[] args) {
//		createDB();
//		seedDB();
//		createDeckCards();
//		createUserCards();
		
		
//		queryDeckCardByUserId();
		
//		consultDB();

//		modifyDeck();
		
		
		
//		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.show_sql", "true");
//		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
//		
//		EntityManagerFactory emf = new HibernatePersistenceProvider()
//		.createContainerEntityManagerFactory(
//				new CustomPersistenceUnitInfo(),
//				props
//		);
//		
//		EntityManager em = emf.createEntityManager();
		
		
		
		

//		DAOManagerImp manager = new DAOManagerImp();
//		UserDAO userDAO = manager.getUserDAO();
//    	Pet pet = new Pet("Conejo", "Salto", 1, Rarity.COMMON);
//        User user = new User("Anna", "123456", pet);
//		userDAO.save(user);
		
//		System.out.println(userDAO.getById(1L));

		
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
	
	
	private static void createDB() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
		.createContainerEntityManagerFactory(
				new CustomPersistenceUnitInfo(),
				props
		);
		
		EntityManager em = emf.createEntityManager();
	}
	
//	private static void seedDB() {
//		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.show_sql", "true");
////		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
//		
//		EntityManagerFactory emf = new HibernatePersistenceProvider()
//		.createContainerEntityManagerFactory(
//				new CustomPersistenceUnitInfo(),
//				props
//		);
//		
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			em.getTransaction().begin();
//			
//			Pet pet = new Pet("Perro", "Velocidad", 2, Rarity.COMMON);
//			User user = new User("Carl", "123456", pet);
//			Card card1 = new Card(
//					"Charles",
//					"A la victoria",
//					"Porta un martillo",
//					"Warrior",
//					Rarity.RARE,
//					"Golpe Fuerte",
//					85,
//					59,
//					148
//			);
//			Card card2 = new Card(
//					"Merlin",
//					"Books for the win",
//					"Has a Castle",
//					"Mage",
//					Rarity.EPIC,
//					"Frezzing Ice",
//					85,
//					59,
//					148
//			);
//			Card card3 = new Card(
//					"Thor",
//					"Lives in azargth",
//					"Has a Hammer",
//					"Nordic",
//					Rarity.ADVANCED,
//					"Launches Hammer",
//					85,
//					59,
//					148
//			);
//			
//			
//			DeckCard deckcard1 = new DeckCard(card1, 1);
//			DeckCard deckcard2 = new DeckCard(card3, 2);
//			DeckCard deckcard3 = new DeckCard(card2, 3);
//			
//			DeckCard deckcard4 = new DeckCard(card2, 1);
//			DeckCard deckcard5 = new DeckCard(card1, 2);
//			
//			DeckCard deckcard6 = new DeckCard(card3, 1);
//			
//			Set<DeckCard> deckCards1 = new HashSet<>();
//			deckCards1.add(deckcard1);
//			deckCards1.add(deckcard2);
//			deckCards1.add(deckcard3);
//	
//			Set<DeckCard> deckCards2 = new HashSet<>();
//			deckCards1.add(deckcard4);
//			deckCards1.add(deckcard5);
//
//			Set<DeckCard> deckCards3 = new HashSet<>();
//			deckCards1.add(deckcard6);
//			
//			Deck deck1 = new Deck(user,deckCards1);
//			Deck deck2 = new Deck(user,deckCards2);
//			Deck deck3 = new Deck(user,deckCards3);
//			
////			em.persist(pet);
//			em.persist(user);
////			em.persist(card1);
////			em.persist(card2);
//			em.persist(deck1);
//			em.persist(deck2);
//			em.persist(deck3);
//			
//			Pet pet1 = new Pet("Gato", "Agilidad", 3, Rarity.COMMON);
//			User user1 = new User("Carlos", "123456", pet1);
//			
//			Pet pet2 = new Pet("Conejo", "Salto", 1, Rarity.COMMON);
//			User user2 = new User("Anna", "123456", pet2);
//			em.persist(pet1);
//			em.persist(user1);
//			em.persist(pet2);
//			em.persist(user2);
//			
//		
//			
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//	}
	
	private static void seedDB() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
//		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
		
		EntityManagerFactory emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(
						new CustomPersistenceUnitInfo(),
						props
						);
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Pet pet = new Pet("Perro", "Velocidad", 2, Rarity.COMMON, "");
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
					148,
					""
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
					148,
					""
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
					148,
					""
					);
			
			
			em.persist(card1);
			em.persist(card2);
			em.persist(card3);
			
//			Set<Card> cards1 = new HashSet<>();
//			cards1.add(card1);
//			cards1.add(card2);
			
//			Set<Card> cards2 = new HashSet<>();
//			cards2.add(card2);
//			cards2.add(card3);
			
//			Deck deck1 = new Deck(user);
//			Deck deck2 = new Deck(user);
			
//			em.persist(pet);
			em.persist(pet);
			em.persist(user);
//			em.persist(card1);
//			em.persist(card2);
//			em.persist(deck1);
//			em.persist(deck2);
			
			Pet pet1 = new Pet("Gato", "Agilidad", 3, Rarity.COMMON, "");
			User user1 = new User("Carlos", "123456", pet1);
			
			Pet pet2 = new Pet("Conejo", "Salto", 1, Rarity.COMMON, "");
			User user2 = new User("Anna", "123456", pet2);
			em.persist(pet1);
			em.persist(user1);
			em.persist(pet2);
			em.persist(user2);

			
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	private static void createDeckCards() {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
//		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
		
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
//		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update (none -> Default)
		
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
	
	
	
	
	
//	private static void queryDeckCardByUserId() {
//		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.show_sql", "true");	
//		
//		EntityManagerFactory emf = new HibernatePersistenceProvider()
//		.createContainerEntityManagerFactory(
//				new CustomPersistenceUnitInfo(),
//				props
//		);
//		
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			em.getTransaction().begin();
//			
//			User user = em.find(User.class, 1);
//			Set<Deck> userDecks = user.getDecks();
//			
//			for (Deck deckItem: userDecks) {
//				Set<Card> deckCards = deckItem.getCards();
//				for (Card cardItem: deckCards) {
//					System.out.println(cardItem);
//				}
//				System.out.println("\n---------------------------\n");
//			}
//		
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//	}
	
//	
//	private static void printJson() {
//		Pet pet = new Pet("Perro", "Velocidad", 2, Rarity.COMMON, "");
//		User user = new User("Carl", "123456", pet);
//		Card card1 = new Card(
//				"Charles",
//				"A la victoria",
//				"Porta un martillo",
//				"Warrior",
//				Rarity.RARE,
//				"Golpe Fuerte",
//				85,
//				59,
//				148,
//				""
//		);
//		Card card2 = new Card(
//				"Merlin",
//				"Books for the win",
//				"Has a Castle",
//				"Mage",
//				Rarity.EPIC,
//				"Frezzing Ice",
//				85,
//				59,
//				148,
//				""
//		);
//		
//		Set<Card> cards = new HashSet<>();
//		cards.add(card1);
//		cards.add(card2);
//		
//		Deck deck = new Deck(user,cards);
//		
//		Gson gson = new Gson();
//		String jsonRequest = gson.toJson(deck);
//		
//		System.out.println(jsonRequest);
//	}
	
	
//	private static void consultDB() {
//		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.show_sql", "true");	
//		
//		EntityManagerFactory emf = new HibernatePersistenceProvider()
//		.createContainerEntityManagerFactory(
//				new CustomPersistenceUnitInfo(),
//				props
//		);
//		
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			em.getTransaction().begin();
//
//			
//			User user4 = em.find(User.class, 1);
//			Set<Deck> userDecks = user4.getDecks();
//			Set<Deck> userDecksModified = new HashSet<>();
//			
//			for (Deck deckItem: userDecks) {
//				
//				Set<Card> deckCards = deckItem.getCards();
//				Deck modifiedDeck = new Deck();
//				modifiedDeck.setId(deckItem.getId());
////				modifiedDeck.setUser(deckItem.getUser());
//				modifiedDeck.setCards(deckCards);
//
//				userDecksModified.add(modifiedDeck);
//			}
//
//			User newUser = new User();
//			newUser.setId(user4.getId());
//			newUser.setUsername(user4.getUsername());
//			newUser.setPet(user4.getPet());
//			newUser.setDecks(userDecksModified);
//
//			
////			Gson gsonb = new GsonBuilder()
////					.registerTypeAdapter(User.class)
////					.excludeFieldsWithoutExposeAnnotation()
////					.create();
//			
//			Gson gson = new Gson();
//			String jsonRequest = gson.toJson(newUser);			
//			System.out.println(jsonRequest);
//			
//			
////			String respuesta = "{\"id\":1,\"username\":\"Carl\",\"pet\":{\"id\":1,\"name\":\"Perro\",\"bonus\":\"Velocidad\",\"level\":2,\"rarity\":\"COMMON\"},\"decks\":[{\"deck\":[{\"id\":3,\"name\":\"Thor\",\"phrase\":\"Lives in azargth\",\"description\":\"Has a Hammer\",\"type\":\"Nordic\",\"rarity\":\"ADVANCED\",\"skill\":\"Launches Hammer\",\"health\":85,\"shield\":59,\"attack\":148},{\"id\":2,\"name\":\"Merlin\",\"phrase\":\"Books for the win\",\"description\":\"Has a Castle\",\"type\":\"Mage\",\"rarity\":\"EPIC\",\"skill\":\"Frezzing Ice\",\"health\":85,\"shield\":59,\"attack\":148}]},{\"deck\":[{\"id\":1,\"name\":\"Charles\",\"phrase\":\"A la victoria\",\"description\":\"Porta un martillo\",\"type\":\"Warrior\",\"rarity\":\"RARE\",\"skill\":\"Golpe Fuerte\",\"health\":85,\"shield\":59,\"attack\":148},{\"id\":2,\"name\":\"Merlin\",\"phrase\":\"Books for the win\",\"description\":\"Has a Castle\",\"type\":\"Mage\",\"rarity\":\"EPIC\",\"skill\":\"Frezzing Ice\",\"health\":85,\"shield\":59,\"attack\":148}]}]}";
//			
//			User target = gson.fromJson(jsonRequest, User.class);
//			System.out.println(target);
////			
//			
//			
////			TypeToken<List<User>> listType = new TypeToken<List<User>>() {};
////			User user4 = em.find(User.class, 1);
////			Gson gson = new Gson();
////			String jsonRequest = gson.toJson(user4, User.class);			
////			System.out.println(jsonRequest);
////			
//			
//			
//			
//			
//			
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//		
//	
//
//	}
	
//	private static void modifyDeck() {
//		String userRequest = "{\"id\":1,\"deck\":[{\"id\":2,\"name\":\"Merlin\",\"phrase\":\"Books for the win\",\"description\":\"Has a Castle\",\"type\":\"Mage\",\"rarity\":\"EPIC\",\"skill\":\"Frezzing Ice\",\"health\":85,\"shield\":59,\"attack\":148},{\"id\":1,\"name\":\"Charles\",\"phrase\":\"A la victoria\",\"description\":\"Porta un martillo\",\"type\":\"Warrior\",\"rarity\":\"RARE\",\"skill\":\"Golpe Fuerte\",\"health\":85,\"shield\":59,\"attack\":148}]}";
//		
//		Gson gson = new Gson();			
//		Deck target = gson.fromJson(userRequest, Deck.class);
//		
//		
//		
//		Map<String, String> props = new HashMap<>();
//		props.put("hibernate.show_sql", "true");	
//		
//		EntityManagerFactory emf = new HibernatePersistenceProvider()
//		.createContainerEntityManagerFactory(
//				new CustomPersistenceUnitInfo(),
//				props
//		);
//		
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			em.getTransaction().begin();
//			
//			User userTargeted = em.find(User.class, 1L);
//			User userModified = new User();
//			userModified.setId(userTargeted.getId());
//		
//			System.out.println(target);
//			
//			
//			
//			
//			
//		
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//		
//		
//		
//		
//		
//
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}





















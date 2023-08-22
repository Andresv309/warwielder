package com.adso.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = -3227907185519750931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name="pet_id")
	private Pet pet;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private Set<Deck> decks;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "users_cards", 
	  joinColumns = {
			  @JoinColumn(name = "user_id", referencedColumnName = "id")
	  },
	  inverseJoinColumns = {
			  @JoinColumn(name = "card_id", referencedColumnName = "id")
	  })
	private Set<Card> cards;
	
	@Column(name = "coins")
	private int coins;

	public User() {
		super();
	}

	public User(String username, String password, Pet pet) {
		super();
		this.username = username;
		this.password = password;
		this.pet = pet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Pet getPet() {
		return pet;
	}


	public void setPet(Pet pet) {
		this.pet = pet;
	}

	
	public Set<Deck> getDecks() {
		return decks;
	}


	public void setDecks(Set<Deck> decks) {
		this.decks = decks;
	}
	

	public Set<Card> getCards() {
		return cards;
	}


	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", pet=" + pet + ", decks="
				+ decks + ", cards=" + cards + ", coins=" + coins + "]";
	}
	
}

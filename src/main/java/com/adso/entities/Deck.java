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
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "decks")
public class Deck implements Serializable {
	private static final long serialVersionUID = -239965973795129879L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "deck_cards", 
	  joinColumns = {
			  @JoinColumn(name = "deck_id", referencedColumnName = "id")
	  },
	  inverseJoinColumns = {
			  @JoinColumn(name = "card_id", referencedColumnName = "id")
	  })
	private Set<Card> deck;

	public Deck() {
		super();
	}

	public Deck(User user, Set<Card> deck) {
		super();
		this.user = user;
		this.deck = deck;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Card> getCards() {
		return deck;
	}

	public void setCards(Set<Card> deck) {
		this.deck = deck;
	}

	@Override
	public String toString() {
		return "Deck [id=" + id + ", user=" + user + ", deck=" + deck + "]";
	}

	
	
}






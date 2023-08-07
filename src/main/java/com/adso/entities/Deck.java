package com.adso.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "deck_cards", 
	  joinColumns = {
			  @JoinColumn(name = "deck_id", referencedColumnName = "id")
	  },
	  inverseJoinColumns = {
			  @JoinColumn(name = "card_id", referencedColumnName = "id")
	  })
	private Set<Card> cards;

	public Deck() {
		super();
	}

	public Deck(User user, Set<Card> cards) {
		super();
		this.user = user;
		this.cards = cards;
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
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Deck [id=" + id + ", user=" + user + ", cards=" + cards + "]";
	}
	
}






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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "deck")
	private Set<DeckCard> deckCards;
	
	public Deck() {
		super();
	}

	public Deck(User user) {
		super();
		this.user = user;
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

	public Set<DeckCard> getDeckCards() {
		return deckCards;
	}

	public void setDeckCards(Set<DeckCard> deckCards) {
		this.deckCards = deckCards;
	}

	@Override
	public String toString() {
		return "Deck [id=" + id + ", user=" + user + ", deckCards=" + deckCards + "]";
	}


}






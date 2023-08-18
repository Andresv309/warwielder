package com.adso.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deck_cards")
public class DeckCard implements Serializable {
	private static final long serialVersionUID = -7465841831545686614L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    private Card card;
    
    @Column(name = "position")
    private int position;

	public DeckCard() {
		super();
	}

	public DeckCard(Deck deck, Card card, int position) {
		super();
		this.deck = deck;
		this.card = card;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "DeckCard [id=" + id + ", deck=" + deck + ", card=" + card + ", position=" + position + "]";
	}

}












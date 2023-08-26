package com.adso.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "store_items")
public class StoreItems implements Serializable {
	private static final long serialVersionUID = -6943678381898038842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
    @Column(name = "created_at", updatable=false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "store_cards", 
	  joinColumns = {
			  @JoinColumn(name = "store_id", referencedColumnName = "id")
	  },
	  inverseJoinColumns = {
			  @JoinColumn(name = "card_id", referencedColumnName = "id")
	  })
	private Set<Card> cards;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "store_pets", 
	  joinColumns = {
			  @JoinColumn(name = "store_id", referencedColumnName = "id")
	  },
	  inverseJoinColumns = {
			  @JoinColumn(name = "pet_id", referencedColumnName = "id")
	  })
	private Set<Pet> pets;

	public StoreItems() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "StoreSelectionItems [id=" + id + ", createdAt=" + createdAt + ", cards=" + cards + ", pets=" + pets
				+ "]";
	}
	
}


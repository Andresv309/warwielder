package com.adso.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="pet_id")
	private Pet pet;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private Set<Deck> decks;

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


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", pet=" + pet + ", decks="
				+ decks + "]";
	}
	
}

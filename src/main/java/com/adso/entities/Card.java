package com.adso.entities;

import java.io.Serializable;

import com.adso.attributeConverters.RarityConverter;
import com.adso.enums.Rarity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card implements Serializable {
	private static final long serialVersionUID = 3048098061228412147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "phrase", nullable = false)
	private String phrase;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Convert(converter = RarityConverter.class)
	@Column(name = "rarity", nullable = false)
	private Rarity rarity;
	
	@Column(name = "skill", nullable = false)
	private String skill;
	
	@Column(name = "health", nullable = false)
	private int health;
	
	@Column(name = "shield", nullable = false)
	private int shield;
	
	@Column(name = "attack", nullable = false)
	private int attack;

	public Card() {
		super();
	}

	public Card(String name, String phrase, String description, String type, Rarity rarity, String skill, int health,
			int shield, int attack) {
		super();
		this.name = name;
		this.phrase = phrase;
		this.description = description;
		this.type = type;
		this.rarity = rarity;
		this.skill = skill;
		this.health = health;
		this.shield = shield;
		this.attack = attack;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", phrase=" + phrase + ", description=" + description + ", type="
				+ type + ", rarity=" + rarity + ", skill=" + skill + ", health=" + health + ", shield=" + shield
				+ ", attack=" + attack + "]";
	}
	
}













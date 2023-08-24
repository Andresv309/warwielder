package com.adso.entities;

import java.io.Serializable;

import com.adso.attributeConverters.RaceConverter;
import com.adso.attributeConverters.RarityConverter;
import com.adso.enums.Race;
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
	
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Convert(converter = RarityConverter.class)
	@Column(name = "rarity", nullable = false)
	private Rarity rarity;
	
	@Convert(converter = RaceConverter.class)
	@Column(name = "race", nullable = false)
	private Race race;
	
	@Column(name = "health", nullable = false)
	private int health;
	
	@Column(name = "cost", nullable = false)
	private int cost;
	
	@Column(name = "attack", nullable = false)
	private int attack;
	
	@Column(name = "src_path", nullable = false)
	private String img;

	public Card() {
		super();
	}

	public Card(String code, String name, String description, Rarity rarity, Race race, int health, int cost, int attack,
			String img) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.rarity = rarity;
		this.race = race;
		this.health = health;
		this.cost = cost;
		this.attack = attack;
		this.img = img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", description=" + description + ", rarity=" + rarity + ", race="
				+ race + ", health=" + health + ", cost=" + cost + ", attack=" + attack + ", img=" + img + "]";
	}

}


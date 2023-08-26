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
@Table(name = "pets")
public class Pet implements Serializable {
	private static final long serialVersionUID = -1179598867311610978L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "bonus", nullable = false)
	private String bonus;
	
	@Convert(converter = RarityConverter.class)
	@Column(name = "rarity", nullable = false)
	private Rarity rarity;
	
	@Column(name = "img", nullable = false)
	private String img;

	public Pet() {
	}
	
	public Pet(String name, String bonus, Rarity rarity, String img) {
		super();
		this.name = name;
		this.bonus = bonus;
		this.rarity = rarity;
		this.img = img;
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

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", bonus=" + bonus + ", rarity=" + rarity + ", img=" + img + "]";
	}

}


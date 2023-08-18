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
	
	@Column(name = "level", nullable = false)
	private int level;
	
	@Convert(converter = RarityConverter.class)
	@Column(name = "rarity", nullable = false)
	private Rarity rarity;
	
	@Column(name = "src_path", nullable = false)
	private String srcPath;

	public Pet() {
	}
	
	
	public Pet(String name, String bonus, int level, Rarity rarity, String srcPath) {
		super();
		this.name = name;
		this.bonus = bonus;
		this.level = level;
		this.rarity = rarity;
		this.srcPath = srcPath;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public String getSrcPath() {
		return srcPath;
	}


	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}


	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", bonus=" + bonus + ", level=" + level + ", rarity=" + rarity
				+ ", srcPath=" + srcPath + "]";
	}

	
	
}









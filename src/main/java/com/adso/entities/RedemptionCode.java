package com.adso.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "redemption_Codes")
public class RedemptionCode implements Serializable {
	private static final long serialVersionUID = -4798034602491325070L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	@Column(name = "isRedeemed ")
	private Boolean isRedeemed = false;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_id", nullable = true)
	private User user;

	public RedemptionCode() {
		super();
	}

	public RedemptionCode(String code) {
		super();
		this.code = code;
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

	public Boolean getIsRedeemed() {
		return isRedeemed;
	}

	public void setIsRedeemed(Boolean isRedeemed) {
		this.isRedeemed = isRedeemed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RedemptionCode [id=" + id + ", code=" + code + ", isRedeemed=" + isRedeemed + ", user=" + user + "]";
	}
	
}

package com.jaruco.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Opinion {

	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	private String opinionDate;
	
	@NotNull
	private Long idStore;
	
	@NotNull
	private Long idUser;
	
	/*
	 * add Unique validation in DB
	 * @Column(unique = true)
	 * */
	@NotNull
	private Long idPurchase;
	
	@NotNull
	private String userComment;
	
	@NotNull
	@Min(0)
	@Max(5)
	private Long punctuation;
	
	private boolean valid;

	public Opinion() {
		super();
	}

	public Opinion(String opinionDate, Long idStore, Long idUser, Long idPurchase, String userComment,
			Long punctuation, boolean valid) {
		super();
		this.opinionDate = opinionDate;
		this.idStore = idStore;
		this.idUser = idUser;
		this.idPurchase = idPurchase;
		this.userComment = userComment;
		this.punctuation = punctuation;
		this.valid = valid;
	}

	public Opinion(Long id, String opinionDate, Long idStore, Long idUser, Long idPurchase, String userComment,
			Long punctuation, boolean valid) {
		super();
		this.id = id;
		this.opinionDate = opinionDate;
		this.idStore = idStore;
		this.idUser = idUser;
		this.idPurchase = idPurchase;
		this.userComment = userComment;
		this.punctuation = punctuation;
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpinionDate() {
		return opinionDate;
	}

	public void setOpinionDate(String opinionDate) {
		this.opinionDate = opinionDate;
	}

	public Long getIdStore() {
		return idStore;
	}

	public void setIdStore(Long idStore) {
		this.idStore = idStore;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Long getPuntuacion() {
		return punctuation;
	}

	public void setPuntuacion(Long punctuation) {
		this.punctuation = punctuation;
	}

	@Override
	public String toString() {
		return "Puntuacion [opinionDate=" + opinionDate + ", idStore=" + idStore + ", idUser=" + idUser
				+ ", idPurchase=" + idPurchase + ", userComment=" + userComment + ", punctuation=" + punctuation
				+ ", valid=" + valid + "]";
	}

	
}
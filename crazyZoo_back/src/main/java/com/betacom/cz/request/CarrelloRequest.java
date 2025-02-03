package com.betacom.cz.request;

public class CarrelloRequest {
	Integer id_utente;

	public Integer getId_utente() {
		return id_utente;
	}

	public void setId_utente(Integer id_utente) {
		this.id_utente = id_utente;
	}

	public CarrelloRequest(Integer id_utente) {
		super();
		this.id_utente = id_utente;
	}

	public CarrelloRequest() {
		super();
	}
	
	
}

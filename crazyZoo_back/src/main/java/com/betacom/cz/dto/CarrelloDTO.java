package com.betacom.cz.dto;

public class CarrelloDTO {
	
	private Integer id;
	private UtenteDTO utente;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public UtenteDTO getUtente() {
		return utente;
	}
	
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	//Costruttori
	public CarrelloDTO(Integer id, UtenteDTO utente) {
		super();
		this.id = id;
		this.utente = utente;
	}
	public CarrelloDTO() {
		super();
	}
	
	//toString
	@Override
	public String toString() {
		return "CarrelloDTO [id=" + id + ", utente=" + utente + "]";
	}

}

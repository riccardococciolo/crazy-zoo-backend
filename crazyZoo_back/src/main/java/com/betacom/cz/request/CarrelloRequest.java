package com.betacom.cz.request;

public class CarrelloRequest {
	
	private Integer id;
	private Integer utenteID;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUtenteID() {
		return utenteID;
	}
	
	public void setUtenteID(Integer utenteID) {
		this.utenteID = utenteID;
	}

	//Costruttori
	public CarrelloRequest(Integer id, Integer utenteID) {
		super();
		this.id = id;
		this.utenteID = utenteID;
	}

	public CarrelloRequest() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "CarrelloRequest [id=" + id + ", utenteID=" + utenteID + "]";
	}
	
}

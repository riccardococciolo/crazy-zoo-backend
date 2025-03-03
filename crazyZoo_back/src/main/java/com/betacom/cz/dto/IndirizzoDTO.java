package com.betacom.cz.dto;

public class IndirizzoDTO {
	
	private String via;
	private String civico;
	private String cap;
	private String citta;
	
	//Getters&Setters
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCivico() {
		return civico;
	}
	
	public void setCivico(String civico) {
		this.civico = civico;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	//Costruttori
	public IndirizzoDTO(String via, String civico, String cap, String citta) {
		super();
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.citta = citta;
	}
	
}

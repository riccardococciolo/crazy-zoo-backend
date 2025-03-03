package com.betacom.cz.dto;

import java.util.List;


public class OrdineDTO {
	
	private Integer id;
	private CarrelloDTO carrello;
	private UtenteDTO utente;
	private List<ProdottoDTO> prodotti;
	private Double totale;
	
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public Double getTotale() {
		return totale;
	}
	
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public CarrelloDTO getCarrello() {
		return carrello;
	}
	
	public void setCarrello(CarrelloDTO carrello) {
		this.carrello = carrello;
	}
	
	public UtenteDTO getUtente() {
		return utente;
	}
	
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	public List<ProdottoDTO> getProdotti() {
		return prodotti;
	}
	
	public void setProdotti(List<ProdottoDTO> prodotti) {
		this.prodotti = prodotti;
	}
	
	//Costruttori
	public OrdineDTO(Integer id, CarrelloDTO carrello, UtenteDTO utente, List<ProdottoDTO> prodotti, Double totale) {
		super();
		this.id = id;
		this.carrello = carrello;
		this.utente = utente;
		this.prodotti = prodotti;
		this.totale = totale;
	}
	
	public OrdineDTO() {
		super();
	}
	
	//toString
	@Override
	public String toString() {
		return "OrdineDTO [id=" + id + ", carrello=" + carrello + ", utente=" + utente + ", prodotti=" + prodotti + "]";
	}

}

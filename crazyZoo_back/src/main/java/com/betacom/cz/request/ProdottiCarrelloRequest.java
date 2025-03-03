package com.betacom.cz.request;

public class ProdottiCarrelloRequest {
	
	private Integer id_carrello;
	private Integer id_prodotti;
	
	//Getters&Setters
	public Integer getId_carrello() {
		return id_carrello;
	}
	
	public void setId_carrello(Integer id_carrello) {
		this.id_carrello = id_carrello;
	}
	
	public Integer getId_prodotti() {
		return id_prodotti;
	}
	
	public void setId_prodotti(Integer id_prodotti) {
		this.id_prodotti = id_prodotti;
	}
	
	//Costruttori
	public ProdottiCarrelloRequest(Integer id_carrello, Integer id_prodotti) {
		super();
		this.id_carrello = id_carrello;
		this.id_prodotti = id_prodotti;
	}
	
}

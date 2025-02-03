package com.betacom.cz.request;

public class RecensioniRequest {
	private Integer id_prodotto;
	private Integer id_utente;
	private Integer valutazione;
	private String descrizione;
	public Integer getId_prodotto() {
		return id_prodotto;
	}
	public void setId_prodotto(Integer id_prodotto) {
		this.id_prodotto = id_prodotto;
	}
	public Integer getId_utente() {
		return id_utente;
	}
	public void setId_utente(Integer id_utente) {
		this.id_utente = id_utente;
	}
	public Integer getValutazione() {
		return valutazione;
	}
	public void setValutazione(Integer valutazione) {
		this.valutazione = valutazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public RecensioniRequest(Integer id_prodotto, Integer id_utente, Integer valutazione, String descrizione) {
		super();
		this.id_prodotto = id_prodotto;
		this.id_utente = id_utente;
		this.valutazione = valutazione;
		this.descrizione = descrizione;
	}
	public RecensioniRequest() {
		super();
	}
	
}

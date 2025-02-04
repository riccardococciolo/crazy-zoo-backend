package com.betacom.cz.dto;

public class RecensioneDTO {

	private Integer id;
	private Integer valutazione;
	private String descrizione;
	private ProdottoDTO prodotto;
	private UtenteDTO utente;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public ProdottoDTO getProdotto() {
		return prodotto;
	}
	
	public void setProdotto(ProdottoDTO prodotto) {
		this.prodotto = prodotto;
	}
	
	public UtenteDTO getUtente() {
		return utente;
	}
	
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	//Costruttori
	public RecensioneDTO(Integer id, Integer valutazione, String descrizione, ProdottoDTO prodotto, UtenteDTO utente) {
		super();
		this.id = id;
		this.valutazione = valutazione;
		this.descrizione = descrizione;
		this.prodotto = prodotto;
		this.utente = utente;
	}
	
	public RecensioneDTO() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "RecensioneDTO [id=" + id + ", valutazione=" + valutazione + ", descrizione=" + descrizione
				+ ", prodotto=" + prodotto + ", utente=" + utente + "]";
	}
	
}

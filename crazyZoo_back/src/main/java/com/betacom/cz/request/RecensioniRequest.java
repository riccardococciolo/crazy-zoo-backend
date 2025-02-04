package com.betacom.cz.request;

public class RecensioniRequest {

	private Integer id;
	private Integer valutazione;
	private String descrizione;
	private Integer prodottoID;
	private Integer utenteID;
	
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
	
	public Integer getProdottoID() {
		return prodottoID;
	}
	
	public void setProdottoID(Integer prodottoID) {
		this.prodottoID = prodottoID;
	}
	
	public Integer getUtenteID() {
		return utenteID;
	}
	
	public void setUtenteID(Integer utenteID) {
		this.utenteID = utenteID;
	}

	//Costruttori
	public RecensioniRequest(Integer id, Integer valutazione, String descrizione, Integer prodottoID,
			Integer utenteID) {
		super();
		this.id = id;
		this.valutazione = valutazione;
		this.descrizione = descrizione;
		this.prodottoID = prodottoID;
		this.utenteID = utenteID;
	}

	public RecensioniRequest() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "RecensioniRequest [id=" + id + ", valutazione=" + valutazione + ", descrizione=" + descrizione
				+ ", prodottoID=" + prodottoID + ", utenteID=" + utenteID + "]";
	}
	
}

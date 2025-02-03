package com.betacom.cz.dto;

public class RecensioneDTO {

	private Integer id;
	private Integer valutazione;
	private String descrizione;
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
	public RecensioneDTO(Integer id, Integer valutazione, String descrizione) {
		super();
		this.id = id;
		this.valutazione = valutazione;
		this.descrizione = descrizione;
	}
	public RecensioneDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}

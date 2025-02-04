package com.betacom.cz.request;

public class ProdottoRequest {
	
	private Integer id;
	private Double prezzo;
	private Integer quantita;
	private String titolo;
	private Integer animaleID;
	private Integer marcaID;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public Integer getAnimaleID() {
		return animaleID;
	}
	
	public void setAnimaleID(Integer animaleID) {
		this.animaleID = animaleID;
	}
	
	public Integer getMarcaID() {
		return marcaID;
	}
	
	public void setMarcaId(Integer marcaID) {
		this.marcaID = marcaID;
	}
	
	//Costruttori
	public ProdottoRequest(Integer id, Double prezzo, Integer quantita, String titolo, Integer animaleID,
			Integer marcaID) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.titolo = titolo;
		this.animaleID = animaleID;
		this.marcaID = marcaID;
	}
	
	public ProdottoRequest() {
		super();
	}
	
	//toString
	@Override
	public String toString() {
		return "ProdottoRequest [id=" + id + ", prezzo=" + prezzo + ", quantita=" + quantita + ", titolo=" + titolo
				+ ", animaleID=" + animaleID + ", marcaID=" + marcaID + "]";
	}
	
}

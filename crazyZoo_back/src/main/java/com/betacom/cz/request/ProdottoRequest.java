package com.betacom.cz.request;

public class ProdottoRequest {
	private Integer id;
	private Double prezzo;
	private Integer quantita;
	private String titolo;
	private Integer animaleId;
	private Integer marcaId;
	
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
	public Integer getAnimaleId() {
		return animaleId;
	}
	public void setAnimaleId(Integer animaleId) {
		this.animaleId = animaleId;
	}
	public Integer getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}
	public ProdottoRequest(Integer id, Double prezzo, Integer quantita, String titolo, Integer animaleId,
			Integer marcaId) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.titolo = titolo;
		this.animaleId = animaleId;
		this.marcaId = marcaId;
	}
	public ProdottoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
}

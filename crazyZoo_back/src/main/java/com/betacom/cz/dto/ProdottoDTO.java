package com.betacom.cz.dto;

public class ProdottoDTO {
	
	private Integer id;
	private Double prezzo;
	private Integer quantita;
	private String titolo;
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
	public ProdottoDTO(Integer id, Double prezzo, Integer quantita, String titolo) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.titolo = titolo;
	}
	public ProdottoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}

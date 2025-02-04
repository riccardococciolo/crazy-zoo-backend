package com.betacom.cz.dto;

public class ProdottoDTO {
	
	private Integer id;
	private Double prezzo;
	private Integer quantita;
	private String titolo;
	private AnimaleDTO animale;
	private MarcaDTO marca;
	
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
	
	public AnimaleDTO getAnimale() {
		return animale;
	}
	
	public void setAnimale(AnimaleDTO animale) {
		this.animale = animale;
	}
	
	public MarcaDTO getMarca() {
		return marca;
	}
	
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
	
	//Costruttori
	public ProdottoDTO(Integer id, Double prezzo, Integer quantita, String titolo, AnimaleDTO animale, MarcaDTO marca) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.titolo = titolo;
		this.animale = animale;
		this.marca = marca;
	}
	
	public ProdottoDTO() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "ProdottoDTO [id=" + id + ", prezzo=" + prezzo + ", quantita=" + quantita + ", titolo=" + titolo
				+ ", animale=" + animale + ", marca=" + marca + "]";
	}
		
}

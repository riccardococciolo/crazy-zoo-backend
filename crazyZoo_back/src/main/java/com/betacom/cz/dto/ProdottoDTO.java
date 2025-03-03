package com.betacom.cz.dto;

import java.util.List;

public class ProdottoDTO {
	
	private Integer id;
	private Double prezzo;
	private Integer quantita;
	private String titolo;
	private AnimaleDTO animale;
	private MarcaDTO marca;
	private TipologiaDTO tipologia;
	private List<ImmagineDTO> immagini;
	private String descrizione;
	
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

	public TipologiaDTO getTipologia() {
		return tipologia;
	}

	public void setTipologia(TipologiaDTO tipologia) {
		this.tipologia = tipologia;
	}
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public List<ImmagineDTO> getImmagini() {
		return immagini;
	}

	public void setImmagini(List<ImmagineDTO> immagini) {
		this.immagini = immagini;
	}

	//Costruttori
	public ProdottoDTO(Integer id, Double prezzo, Integer quantita, String titolo, AnimaleDTO animale, MarcaDTO marca,
			TipologiaDTO tipologia, List<ImmagineDTO> immagini, String descrizione) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.titolo = titolo;
		this.animale = animale;
		this.marca = marca;
		this.tipologia = tipologia;
		this.immagini = immagini;
		this.descrizione = descrizione;
	}

	public ProdottoDTO() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "ProdottoDTO [id=" + id + ", prezzo=" + prezzo + ", quantita=" + quantita + ", titolo=" + titolo
				+ ", animale=" + animale + ", marca=" + marca + ", tipologia=" + tipologia + "]";
	}
			
}

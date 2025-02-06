package com.betacom.cz.request;

import org.springframework.web.multipart.MultipartFile;

public class ProdottoRequest {
	
	private Integer id;
	private Double prezzo;
	private Integer quantita;
	private String titolo;
	private Integer animaleID;
	private Integer marcaID;
	private Integer tipologiaID;
	private MultipartFile[] immagini;
	private String descrizione;
	
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
	public void setMarcaID(Integer marcaID) {
		this.marcaID = marcaID;
	}
	public Integer getTipologiaID() {
		return tipologiaID;
	}
	public void setTipologiaID(Integer tipologiaID) {
		this.tipologiaID = tipologiaID;
	}
	public MultipartFile[] getImmagini() {
		return immagini;
	}
	public void setImmagini(MultipartFile[] immagini) {
		this.immagini = immagini;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public ProdottoRequest(Integer id, Double prezzo, Integer quantita, String titolo, Integer animaleID,
			Integer marcaID, Integer tipologiaID, MultipartFile[] immagini, String descrizione) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.titolo = titolo;
		this.animaleID = animaleID;
		this.marcaID = marcaID;
		this.tipologiaID = tipologiaID;
		this.immagini = immagini;
		this.descrizione = descrizione;
	}
	public ProdottoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProdottoRequest [id=" + id + ", prezzo=" + prezzo + ", quantita=" + quantita + ", titolo=" + titolo
				+ ", animaleID=" + animaleID + ", marcaID=" + marcaID + ", tipologiaID=" + tipologiaID + ", immagini="
				+ immagini + "]";
	}
	
	
	
}

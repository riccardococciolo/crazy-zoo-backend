package com.betacom.cz.request;

import org.springframework.web.multipart.MultipartFile;

public class ImmagineRequest {
	
	private Integer id;
	private String nomeImmagine;
	private String tipoFile;
	private MultipartFile data;
	private Integer prodottoID;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNomeImmagine() {
		return nomeImmagine;
	}
	
	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}
	
	public String getTipoFile() {
		return tipoFile;
	}
	
	public void setTipoFile(String tipoFile) {
		this.tipoFile = tipoFile;
	}
	
	public MultipartFile getData() {
		return data;
	}
	
	public void setData(MultipartFile data) {
		this.data = data;
	}
	
	public Integer getProdottoID() {
		return prodottoID;
	}
	
	public void setProdottoID(Integer prodottoID) {
		this.prodottoID = prodottoID;
	}
	
	//Costruttori
	public ImmagineRequest(Integer id, String nomeImmagine, String tipoFile, MultipartFile data, Integer prodottoID) {
		super();
		this.id = id;
		this.nomeImmagine = nomeImmagine;
		this.tipoFile = tipoFile;
		this.data = data;
		this.prodottoID = prodottoID;
	}
	
	//toString
	@Override
	public String toString() {
		return "ImmagineRequest [id=" + id + ", nomeImmagine=" + nomeImmagine + ", tipoFile=" + tipoFile + ", data="
				+ data + ", prodottoID=" + prodottoID + "]";
	}

}

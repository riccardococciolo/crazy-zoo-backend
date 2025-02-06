package com.betacom.cz.dto;

public class ImmagineDTO {
	private Integer id;
	private String nomeImmagine;
	private String tipoFile;
	private byte[] data;
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
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public ImmagineDTO(Integer id, String nomeImmagine, String tipoFile, byte[] data) {
		super();
		this.id = id;
		this.nomeImmagine = nomeImmagine;
		this.tipoFile = tipoFile;
		this.data = data;
	
	}
	public ImmagineDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ImmagineDTO [id=" + id + ", nomeImmagine=" + nomeImmagine + ", tipoFile=" + tipoFile + ", data=" + data
				+ ", prodotto=" + "]";
	}
	
	
}

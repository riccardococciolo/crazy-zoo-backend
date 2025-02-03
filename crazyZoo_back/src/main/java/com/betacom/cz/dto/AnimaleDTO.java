package com.betacom.cz.dto;

public class AnimaleDTO {

	private Integer id;
	private String nomeAnimale;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeAnimale() {
		return nomeAnimale;
	}
	public void setNomeAnimale(String nomeAnimale) {
		this.nomeAnimale = nomeAnimale;
	}
	public AnimaleDTO(Integer id, String nomeAnimale) {
		super();
		this.id = id;
		this.nomeAnimale = nomeAnimale;
	}
	public AnimaleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}

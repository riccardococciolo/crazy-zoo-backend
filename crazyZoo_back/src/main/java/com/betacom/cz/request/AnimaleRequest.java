package com.betacom.cz.request;

public class AnimaleRequest {
	
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
	public AnimaleRequest(Integer id, String nomeAnimale) {
		super();
		this.id = id;
		this.nomeAnimale = nomeAnimale;
	}
	public AnimaleRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
}

package com.betacom.cz.dto;

public class MarcaDTO {
	
	private Integer id;
	private String nomeMarca;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	public MarcaDTO(Integer id, String nomeMarca) {
		super();
		this.id = id;
		this.nomeMarca = nomeMarca;
	}
	public MarcaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}

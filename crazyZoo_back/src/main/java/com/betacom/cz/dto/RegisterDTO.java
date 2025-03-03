package com.betacom.cz.dto;

public class RegisterDTO {
	
	private Integer id;

	//Getters&Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//Costruttori
	public RegisterDTO(Integer id) {
		super();
		this.id = id;
	}
	
}

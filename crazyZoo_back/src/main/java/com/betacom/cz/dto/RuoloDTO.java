package com.betacom.cz.dto;

public class RuoloDTO {
	
	private Integer id;
	private String nome;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Costruttori
	public RuoloDTO() {
		super();
	}
	
	public RuoloDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "RuoloDTO [id=" + id + ", nome=" + nome + "]";
	}

}

package com.betacom.cz.dto;

public class AnimaleDTO {

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
	public AnimaleDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public AnimaleDTO() {
		super();
	}
	
	//toString
	@Override
	public String toString() {
		return "AnimaleDTO [id=" + id + ", nome=" + nome + "]";
	}
	
}

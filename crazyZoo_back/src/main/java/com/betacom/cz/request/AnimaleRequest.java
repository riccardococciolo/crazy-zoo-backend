package com.betacom.cz.request;

public class AnimaleRequest {
	
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
	
	public void setNomeAnimale(String nome) {
		this.nome = nome;
	}
	
	//Costruttori
	public AnimaleRequest(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public AnimaleRequest() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "AnimaleRequest [id=" + id + ", nome=" + nome + "]";
	}
	
}

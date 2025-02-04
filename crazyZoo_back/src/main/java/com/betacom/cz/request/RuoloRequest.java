package com.betacom.cz.request;

public class RuoloRequest {

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
	public RuoloRequest() {
		super();
	}

	public RuoloRequest(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	//toString
	@Override
	public String toString() {
		return "RuoloRequest [id=" + id + ", nome=" + nome + "]";
	}
	
}

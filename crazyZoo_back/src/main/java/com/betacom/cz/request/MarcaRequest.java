package com.betacom.cz.request;

public class MarcaRequest {
	
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
	public MarcaRequest(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public MarcaRequest() {
		super();
	}

	//toString
	@Override
	public String toString() {
		return "MarcaRequest [id=" + id + ", nome=" + nome + "]";
	}
	
}

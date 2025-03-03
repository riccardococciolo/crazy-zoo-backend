package com.betacom.cz.request;

public class TipologiaRequest {
	
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
	public TipologiaRequest(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public TipologiaRequest() {
		super();
	}
	
	//toString
	@Override
	public String toString() {
		return "TipologiaRequest [id=" + id + ", nome=" + nome + "]";
	}
		
}

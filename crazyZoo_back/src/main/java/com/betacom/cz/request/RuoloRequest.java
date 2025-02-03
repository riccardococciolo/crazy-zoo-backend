package com.betacom.cz.request;

public class RuoloRequest {
	
	private String nome;

	//Getters&Setters
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

	public RuoloRequest(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "RuoloRequest [nome=" + nome + "]";
	}

}

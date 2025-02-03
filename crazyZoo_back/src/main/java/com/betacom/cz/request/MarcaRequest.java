package com.betacom.cz.request;

public class MarcaRequest {
	String nome_mamrca;

	public String getNome_mamrca() {
		return nome_mamrca;
	}

	public void setNome_mamrca(String nome_mamrca) {
		this.nome_mamrca = nome_mamrca;
	}

	public MarcaRequest(String nome_mamrca) {
		super();
		this.nome_mamrca = nome_mamrca;
	}

	public MarcaRequest() {
		super();
	}
	
	

}

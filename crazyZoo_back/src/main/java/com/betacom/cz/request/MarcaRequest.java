package com.betacom.cz.request;

public class MarcaRequest {
	String nomeMarca;

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public MarcaRequest(String nomeMarca) {
		super();
		this.nomeMarca = nomeMarca;
	}

	public MarcaRequest() {
		super();
	}
	
}

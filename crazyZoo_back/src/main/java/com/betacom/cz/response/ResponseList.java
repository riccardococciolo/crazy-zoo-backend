package com.betacom.cz.response;

import java.util.List;

public class ResponseList<T> extends ResponseBase{
	
	private List<T> dati;

	//Getters&Setters
	public List<T> getDati() {
		return dati;
	}

	public void setDati(List<T> dati) {
		this.dati = dati;
	}
	
}

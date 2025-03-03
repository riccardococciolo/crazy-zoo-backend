package com.betacom.cz.response;

public class ResponseObject<T> extends ResponseBase {
	
	private T dati;

	//Getters&Setters
	public T getDati() {
		return dati;
	}

	public void setDati(T dati) {
		this.dati = dati;
	}
	
}

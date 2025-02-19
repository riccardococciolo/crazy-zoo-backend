package com.betacom.cz.request;

public class ProdottoOrdineRequest {
	
	private Integer prodottoID;
	private Integer ordineID;
	public Integer getProdottoID() {
		return prodottoID;
	}
	public void setProdottoID(Integer prodottoID) {
		this.prodottoID = prodottoID;
	}
	public Integer getOrdineID() {
		return ordineID;
	}
	public void setOrdineID(Integer ordineID) {
		this.ordineID = ordineID;
	}
	
	public ProdottoOrdineRequest(Integer prodottoID, Integer ordineID) {
		super();
		this.prodottoID = prodottoID;
		this.ordineID = ordineID;
	}
	public ProdottoOrdineRequest() {
		super();
	}
	
	
	
	

}

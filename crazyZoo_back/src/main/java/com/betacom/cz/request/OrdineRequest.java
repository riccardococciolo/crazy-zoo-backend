package com.betacom.cz.request;


public class OrdineRequest {
	

	private Integer id;
//	private Integer carrelloID;
	private Integer utenteID;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Integer getCarrelloID() {
//		return carrelloID;
//	}
//	public void setCarrelloID(Integer carrelloID) {
//		this.carrelloID = carrelloID;
//	}
	public Integer getUtenteID() {
		return utenteID;
	}
	public void setUtenteID(Integer utenteID) {
		this.utenteID = utenteID;
	}
	public OrdineRequest(Integer id, Integer utenteID) {
		super();
		this.id = id;
//		this.carrelloID = carrelloID;
		this.utenteID = utenteID;
	}
	public OrdineRequest() {
		super();
	}
	@Override
	public String toString() {
		return "OrdineRequest [id=" + "id + utenteID=" + utenteID + "]";
	}
	
	

}

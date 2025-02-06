package com.betacom.cz.request;

public class ProdottoOrdineRequest {
	
	private ProdottoRequest reqP;
	private OrdineRequest reqO;
	
	public ProdottoRequest getReqP() {
		return reqP;
	}
	public void setReqP(ProdottoRequest reqP) {
		this.reqP = reqP;
	}
	public OrdineRequest getReqO() {
		return reqO;
	}
	public void setReqOs(OrdineRequest reqO) {
		this.reqO = reqO;
	}
	public ProdottoOrdineRequest(ProdottoRequest reqP, OrdineRequest reqO) {
		super();
		this.reqP = reqP;
		this.reqO = reqO;
	}
	public ProdottoOrdineRequest() {
		super();
	}
	@Override
	public String toString() {
		return "ProdottoOrdineRequest [reqP=" + reqP + ", reqO=" + reqO + "]";
	}

	
	

}

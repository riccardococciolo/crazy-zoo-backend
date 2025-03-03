package com.betacom.cz.services.interfaces;

import com.betacom.cz.request.ProdottoOrdineRequest;

public interface ProdottoOrdineServices {
	
    void aggiungiProdottoAOrdine(ProdottoOrdineRequest reqPO);
    void removeAll(ProdottoOrdineRequest reqPO);
    void removeById(ProdottoOrdineRequest reqPO);
    
}

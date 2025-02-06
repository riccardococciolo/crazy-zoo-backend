package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.request.ProdottoOrdineRequest;

public interface ProdottoOrdineServices {
	
    void aggiungiProdottoAOrdine(ProdottoOrdineRequest reqPO);
    void removeAll(ProdottoOrdineRequest reqPO);
    void removeById(ProdottoOrdineRequest reqPO);
    List<Prodotto> listaProdottiInOrdine(ProdottoOrdineRequest reqPO);
    
}

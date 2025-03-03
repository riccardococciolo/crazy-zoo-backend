package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.ProdottiCarrelloRequest;

public interface ProdottiCarrelloServices {
	
	void addCarrello(ProdottiCarrelloRequest req) throws Exception;
	void deleteAllProdInCarrello(ProdottiCarrelloRequest req) throws Exception;
	void deleteProdByIdInCarrello(ProdottiCarrelloRequest req) throws Exception;
	List<ProdottoDTO> listByIdcarrello(ProdottiCarrelloRequest req) throws Exception;

}

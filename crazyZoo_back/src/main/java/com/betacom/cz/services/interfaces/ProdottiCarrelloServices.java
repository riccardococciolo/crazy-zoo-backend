package com.betacom.cz.services.interfaces;

import com.betacom.cz.request.ProdottiCarrelloRequest;

public interface ProdottiCarrelloServices {
	void addCarrello(ProdottiCarrelloRequest req) throws Exception;
	void deleteAllProdInCarrello(ProdottiCarrelloRequest req) throws Exception;
	void deleteProdByIdInCarrello(ProdottiCarrelloRequest req) throws Exception;

}

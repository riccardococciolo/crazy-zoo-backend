package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.CarrelloDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.CarrelloRequest;


public interface CarrelloServices {
	void create(CarrelloRequest req) throws Exception;
	
	void delete(CarrelloRequest req) throws Exception;
	
	List<CarrelloDTO> listAll();
	
	List<ProdottoDTO> listProdotto(Integer id_utente) throws Exception;
	

}

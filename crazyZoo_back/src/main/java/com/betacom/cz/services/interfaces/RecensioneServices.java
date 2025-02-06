package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.RecensioneDTO;
import com.betacom.cz.request.RecensioniRequest;

public interface RecensioneServices {
	
	void create(RecensioniRequest req) throws Exception;
	void delete(RecensioniRequest req) throws Exception;
	
	List<RecensioneDTO> listByProdotto(Integer id) throws Exception;
	List<RecensioneDTO> listByUtente(Integer id) throws Exception;
}

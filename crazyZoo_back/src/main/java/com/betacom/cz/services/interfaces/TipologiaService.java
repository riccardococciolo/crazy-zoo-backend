package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.TipologiaDTO;
import com.betacom.cz.request.TipologiaRequest;

public interface TipologiaService {
	
	void create(TipologiaRequest req) throws Exception;
	void update(TipologiaRequest req) throws Exception;
	void delete(TipologiaRequest req) throws Exception;
	
	List<TipologiaDTO> listAll();
	TipologiaDTO listByID(Integer id);
	

}

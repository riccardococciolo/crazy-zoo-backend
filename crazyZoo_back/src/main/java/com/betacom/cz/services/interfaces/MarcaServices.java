package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;

public interface MarcaServices {

	void create(MarcaRequest req) throws Exception;
	void update(MarcaRequest req) throws Exception;
	void delete(MarcaRequest req) throws Exception;
	
	List<MarcaDTO> listAll();
	
	MarcaDTO listByID(Integer id);
		
}

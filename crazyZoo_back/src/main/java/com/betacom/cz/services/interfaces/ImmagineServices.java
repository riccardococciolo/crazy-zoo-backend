package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.ImmagineDTO;
import com.betacom.cz.request.ImmagineRequest;

public interface ImmagineServices {
void create(ImmagineRequest req) throws Exception;
	
	void update(ImmagineRequest req) throws Exception;
	
	void delete(ImmagineRequest req) throws Exception;
	
	List<ImmagineDTO> listAll();
	
	ImmagineDTO listById(Integer id);
}

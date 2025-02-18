package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.request.AnimaleRequest;

public interface AnimaleServices {

	void create(AnimaleRequest req) throws Exception;
	void update(AnimaleRequest req) throws Exception;
	void delete(AnimaleRequest req) throws Exception;
	
	List<AnimaleDTO> listAll();
	
	AnimaleDTO listByID(Integer id);
}

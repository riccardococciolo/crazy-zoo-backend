package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.RuoloDTO;
import com.betacom.cz.request.RuoloRequest;

public interface RuoloServices {
	
	void create(RuoloRequest req) throws Exception;
	void update(RuoloRequest req) throws Exception;
	void delete(RuoloRequest req) throws Exception;
	
	List<RuoloDTO> listAll();
	
}

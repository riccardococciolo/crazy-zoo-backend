package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;

public interface MarcaServices {

	void create(MarcaRequest req);
	void update(Integer id, MarcaRequest req);
	void delete(Integer id);
	List<MarcaDTO> listAll();
}

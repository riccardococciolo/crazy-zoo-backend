package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;

public interface MarcaServices {

	void create(MarcaRequest req);
	void updateByName(MarcaRequest req, String nomeMarca);
	void delete(String nomeMarca);
	List<MarcaDTO> listAll();
}

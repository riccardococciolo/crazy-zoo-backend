package com.betacom.cz.services.interfaces;

import java.util.List;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.request.UtenteRequest;

public interface UtenteServices {
	
	void create (UtenteRequest req);
	void update (UtenteRequest req, String email);
	void delete (String email);
	List<UtenteDTO> listAll();

}

package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.request.UtenteRequest;

public interface UtenteServices 
{
	void create(UtenteRequest req) throws Exception;
	void delete(UtenteRequest req) throws Exception;
	
	List<UtenteDTO> listAll();
	UtenteDTO listByID(Integer id) throws Exception;
	
}

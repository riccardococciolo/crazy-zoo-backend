package com.betacom.cz.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.services.interfaces.UtenteServices;

@Service
public class UtenteImplementation implements UtenteServices{

	@Override
	public void create(UtenteRequest req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UtenteRequest req, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UtenteDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

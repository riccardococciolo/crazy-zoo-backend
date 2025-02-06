package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.ProdottoRequest;

public interface ProdottoServices {
	void create(ProdottoRequest req) throws Exception;
	
	void update(ProdottoRequest req) throws Exception;
	
	void delete(ProdottoRequest req) throws Exception;
	
	List<ProdottoDTO> list(Integer id, String titolo, Double prezzo, 
			Integer quantita, String nomeAnimale, String nomeTipologia, 
			String nomeMarca, String descrizione);
	
	ProdottoDTO listById(Integer id);
}

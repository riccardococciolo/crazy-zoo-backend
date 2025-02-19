package com.betacom.cz.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.ProdottoRequest;

public interface ProdottoServices {
	void create(ProdottoRequest req) throws Exception;
	
	void update(ProdottoRequest req) throws Exception;
	
	void delete(ProdottoRequest req) throws Exception;
	
	Page<ProdottoDTO> list(Integer id, String titolo, Double prezzoMin, Double prezzoMax, 
			Integer quantita, String nomeAnimale, String nomeTipologia, 
			String nomeMarca, String descrizione, Pageable pageable);
	
	ProdottoDTO listById(Integer id);
}

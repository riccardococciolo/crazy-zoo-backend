package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.request.OrdineRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.OrdineServices;

@RestController
@RequestMapping("/rest/ordini")
public class OrdineController {
	
	@Autowired
	OrdineServices ordineS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody OrdineRequest req) {
        log.info("Inizio creazione ordine per utente ID: {} e carrello ID: {}", req.getUtenteID());



		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ordineS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}
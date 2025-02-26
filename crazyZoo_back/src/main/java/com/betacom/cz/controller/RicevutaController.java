package com.betacom.cz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.RicevutaServices;

@RestController
@RequestMapping("/rest/ricevuta")
@CrossOrigin(origins = "${url_api}")
public class RicevutaController {
	
	@Autowired
	RicevutaServices ricevutaS;
	
	@GetMapping("/send")
	public ResponseBase send(Integer id) {

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ricevutaS.sendRicevuta(id);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

package com.betacom.cz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.betacom.cz.request.ProdottiCarrelloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.ProdottiCarrelloServices;

@RestController
@RequestMapping("/rest/prodcarr")
public class ProdottiCarrelloController {
	@Autowired
	ProdottiCarrelloServices prodS;
	
	
	@PostMapping("/addc")
	public ResponseBase addCarrello(@RequestBody ProdottiCarrelloRequest req) {
	


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			prodS.addCarrello(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

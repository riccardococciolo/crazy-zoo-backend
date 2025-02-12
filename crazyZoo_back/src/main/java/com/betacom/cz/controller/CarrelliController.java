package com.betacom.cz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.cz.request.CarrelloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.CarrelloServices;

@RestController
@RequestMapping("/rest/carrelli")
@CrossOrigin(origins = "${url_api}")
public class CarrelliController {
	
	@Autowired
	CarrelloServices carrS;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody CarrelloRequest req) {
		


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carrS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody CarrelloRequest req) {
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carrS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

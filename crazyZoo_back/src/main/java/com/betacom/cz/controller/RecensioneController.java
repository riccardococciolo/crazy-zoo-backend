package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.cz.dto.RecensioneDTO;
import com.betacom.cz.request.RecensioniRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.services.interfaces.RecensioneServices;

@RestController
@RequestMapping("/rest/recensioni")
@CrossOrigin(origins = "${url_api}")
public class RecensioneController {

	@Autowired
	RecensioneServices recS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody RecensioniRequest req) {
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			recS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody RecensioniRequest req) {
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			recS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listbyprodotto")
	public ResponseList<RecensioneDTO> listByProdotto(@RequestParam Integer id) {
		log.debug("Inizio listByProdotto:");
		
		ResponseList<RecensioneDTO> r = new ResponseList<RecensioneDTO>();
		r.setRc(true);
		try{
			r.setDati(recS.listByProdotto(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listbyutente")
	public ResponseList<RecensioneDTO> listByUtente(@RequestParam Integer id) {
		log.debug("Inizio listById:");
		
		ResponseList<RecensioneDTO> r = new ResponseList<RecensioneDTO>();
		r.setRc(true);
		try{
			r.setDati(recS.listByUtente(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
}

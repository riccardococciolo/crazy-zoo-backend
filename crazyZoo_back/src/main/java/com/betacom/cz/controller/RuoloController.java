package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.dto.RuoloDTO;
import com.betacom.cz.request.RuoloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.services.interfaces.RuoloServices;


@RestController
@RequestMapping("/rest/ruoli")
public class RuoloController {
	
	@Autowired
	RuoloServices ruoloS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody RuoloRequest req) {
		log.debug("Inizio creazione ruolo: {}", req.getNome());


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ruoloS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody RuoloRequest req) {
		log.debug("Inizio update:");
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ruoloS.update(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody RuoloRequest req) {
		log.debug("Inizio delete ruolo: {}", req.getNome());
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ruoloS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/list")
	public ResponseList<RuoloDTO> listAll() {
		log.debug("Inizio listAll:");
		
		ResponseList<RuoloDTO> r = new ResponseList<RuoloDTO>();
		r.setRc(true);
		try{
			r.setDati(ruoloS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}	

}

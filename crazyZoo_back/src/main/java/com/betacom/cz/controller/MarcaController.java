package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.MarcaServices;
import com.betacom.cz.response.ResponseList;

@RestController
@RequestMapping("/rest/marche")

public class MarcaController {
	
	@Autowired
	MarcaServices marcaS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody MarcaRequest req) {
		log.debug("Inizio creazione marca: {}", req.getNomeMarca());


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			marcaS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update/{nomeMarca}")
	public ResponseBase update(@RequestBody MarcaRequest req, @PathVariable String nomeMarca) {
		log.debug("Inizio update marca: {}", nomeMarca);
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			marcaS.updateByName(req, nomeMarca);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete/{nomeMarca}")
	public ResponseBase delete(@PathVariable String nomeMarca) {
		log.debug("Inizio delete marca: {}", nomeMarca);
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			marcaS.deleteByName(nomeMarca);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/list")
	public ResponseList<MarcaDTO> listAll() {
		log.debug("Inizio listAll");
		
		ResponseList<MarcaDTO> r = new ResponseList<MarcaDTO>();
		r.setRc(true);
		try{
			r.setDati(marcaS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

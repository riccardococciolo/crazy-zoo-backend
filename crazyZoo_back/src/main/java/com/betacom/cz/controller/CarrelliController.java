package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.dto.CarrelloDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.CarrelloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.CarrelloServices;

@RestController
@RequestMapping("/rest/carrelli")
public class CarrelliController {
	
	@Autowired
	CarrelloServices carrS;
	
	@Autowired
	Logger log;
	
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
	
	@GetMapping("/listbyid")
	public ResponseList<ProdottoDTO> listByID(@RequestParam Integer id) {
	
		
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		r.setRc(true);
		try{
			r.setDati(carrS.listProdotto(id));
		} catch (Exception e) {
			
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listAll")
	public ResponseList<CarrelloDTO> listAll() {
		log.debug("Inizio listAll:");
		
		ResponseList<CarrelloDTO> r = new ResponseList<CarrelloDTO>();
		r.setRc(true);
		try{
			r.setDati(carrS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

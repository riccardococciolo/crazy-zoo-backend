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
import com.betacom.cz.dto.TipologiaDTO;
import com.betacom.cz.request.TipologiaRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.TipologiaService;

@RestController
@RequestMapping("/rest/tipologie")
@CrossOrigin(origins = "${url_api}")
public class TipologiaController {
	
	@Autowired
	TipologiaService tipologiaS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody TipologiaRequest req) {

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			tipologiaS.create(req);
		}catch(Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody TipologiaRequest req) {
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			tipologiaS.update(req);
		}catch(Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody TipologiaRequest req) {
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			tipologiaS.delete(req);
		}catch(Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listall")
	public ResponseList<TipologiaDTO> listAll() {
		
		ResponseList<TipologiaDTO> r = new ResponseList<TipologiaDTO>();
		r.setRc(true);
		
		try{
			r.setDati(tipologiaS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listbyid")
	public ResponseObject<TipologiaDTO> listByID(@RequestParam Integer id) {
		
		ResponseObject<TipologiaDTO> r = new ResponseObject<TipologiaDTO>();
		r.setRc(true);
		
		try{
			r.setDati(tipologiaS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

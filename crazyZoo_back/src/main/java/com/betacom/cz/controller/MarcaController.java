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
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.MarcaServices;

@RestController
@RequestMapping("/rest/marche")
@CrossOrigin(origins = "${url_api}")
public class MarcaController {
	
	@Autowired
	MarcaServices marcaS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody MarcaRequest req) {

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			marcaS.create(req);
		}catch(Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody MarcaRequest req) {
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			marcaS.update(req);
		}catch(Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody MarcaRequest req) {
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		
		try {
			marcaS.delete(req);
		}catch(Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listall")
	public ResponseList<MarcaDTO> listAll() {
		
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
	
	@GetMapping("/listbyid")
	public ResponseObject<MarcaDTO> listByID(@RequestParam Integer id) {
		
		ResponseObject<MarcaDTO> r = new ResponseObject<MarcaDTO>();
		r.setRc(true);
		
		try{
			r.setDati(marcaS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
}

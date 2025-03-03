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
import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.request.AnimaleRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.AnimaleServices;

@RestController
@RequestMapping("/rest/animali")
@CrossOrigin(origins = "${url_api}")
public class AnimaleController {

	@Autowired
	AnimaleServices animaleS;

	@Autowired
	Logger log;

	@PostMapping("/create")
	public ResponseBase create(@RequestBody AnimaleRequest req) {
		log.debug("Inizio creazione animale: {}", req.getNome());

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			animaleS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PostMapping("/update")
	public ResponseBase update(@RequestBody AnimaleRequest req) {
		log.debug("Inizio update animale:");

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			animaleS.update(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody AnimaleRequest req) {
		log.debug("Inizio delete animale: {}", req.getNome());

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			animaleS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@GetMapping("/listall")
	public ResponseList<AnimaleDTO> listAll() {
		log.debug("Inizio listAll:");

		ResponseList<AnimaleDTO> r = new ResponseList<AnimaleDTO>();
		r.setRc(true);
		try{
			r.setDati(animaleS.listAll());
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@GetMapping("/listbyid")
	public ResponseObject<AnimaleDTO> listByID(@RequestParam Integer id) {
		log.debug("Inizio listAll:");

		ResponseObject<AnimaleDTO> r = new ResponseObject<AnimaleDTO>();
		r.setRc(true);
		try{
			r.setDati(animaleS.listByID(id));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

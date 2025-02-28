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
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.UtenteServices;

@RestController
@RequestMapping("/rest/utente")
@CrossOrigin(origins = "${url_api}")
public class UtenteController
{
	@Autowired
	UtenteServices userS;

	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody UtenteRequest req) {
		log.debug("Inizio creazione Utente: {},{},{},{},{}", 
				req.getNome(),
				req.getCognome(),
				req.getCellulare(),
				req.getEmail(),
				req.getUsername(),
				req.getPassword(),
				req.getRuolo());

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			userS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody UtenteRequest req) {
		log.debug("Inizio delete Utente: {}", req.getNome());
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			userS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	
	@GetMapping("/listall")
	public ResponseList<UtenteDTO> listAll() {
		log.debug("Inizio listAll:");
		
		ResponseList<UtenteDTO> r = new ResponseList<UtenteDTO>();
		r.setRc(true);
		try{
			r.setDati(userS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listbyid")
	public ResponseObject<UtenteDTO> listByID(@RequestParam Integer id) {
		log.debug("Inizio listById:");
		
		ResponseObject<UtenteDTO> r = new ResponseObject<UtenteDTO>();
		r.setRc(true);
		try{
			r.setDati(userS.listByID(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/updaterole")
	public ResponseBase updateRole(@RequestParam Integer id) {
		log.debug("Inizio updateRole:");
	
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try{
			userS.updateRole(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	
	
}

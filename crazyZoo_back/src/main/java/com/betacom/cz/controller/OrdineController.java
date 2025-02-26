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
import com.betacom.cz.dto.OrdineDTO;
import com.betacom.cz.request.OrdineRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.OrdineServices;

@RestController
@RequestMapping("/rest/ordini")
@CrossOrigin(origins = "${url_api}")
public class OrdineController {
	
	@Autowired
	OrdineServices ordineS;
	
	@Autowired
	Logger log;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody OrdineRequest req) {



		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ordineS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody OrdineRequest req) {



		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			ordineS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listall")
	public ResponseList<OrdineDTO> listAll(){
		log.debug("inizio listaAll ordini");
		
		ResponseList<OrdineDTO> r = new ResponseList<OrdineDTO>();
		r.setRc(true);
		try{
			r.setDati(ordineS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listbyid")
	public ResponseObject<OrdineDTO> listByID(@RequestParam Integer id) {
		log.debug("Inizio listById:");
		
		ResponseObject<OrdineDTO> r = new ResponseObject<OrdineDTO>();
		r.setRc(true);
		try{
			r.setDati(ordineS.listById(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listbyutente")
	public ResponseList<OrdineDTO> listByUtente(@RequestParam Integer id){
		log.debug("inizio listutente ordini");
		
		ResponseList<OrdineDTO> r = new ResponseList<OrdineDTO>();
		r.setRc(true);
		try{
			r.setDati(ordineS.listByIdUtente(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	

}
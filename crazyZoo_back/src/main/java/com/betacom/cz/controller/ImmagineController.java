package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.dto.ImmagineDTO;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.services.interfaces.ImmagineServices;

@RestController
@RequestMapping("/rest/immagine")
@CrossOrigin(origins = "${url_api}")
public class ImmagineController {
	
	@Autowired
	ImmagineServices immS;
	
	@Autowired
	Logger log;
	
	@GetMapping("/listall")
	public ResponseList<ImmagineDTO> listAll() {
		
		ResponseList<ImmagineDTO> r = new ResponseList<ImmagineDTO>();
		r.setRc(true);
		
		try{
			r.setDati(immS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());

			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
}

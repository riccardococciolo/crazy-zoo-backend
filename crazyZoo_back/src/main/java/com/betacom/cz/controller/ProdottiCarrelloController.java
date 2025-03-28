package com.betacom.cz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.ProdottiCarrelloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.services.interfaces.ProdottiCarrelloServices;

@RestController
@RequestMapping("/rest/prodcarr")
@CrossOrigin(origins = "${url_api}")
public class ProdottiCarrelloController {
	@Autowired
	ProdottiCarrelloServices prodS;
	
	
	@PostMapping("/addprodottotocarrello")
	public ResponseBase addProdottoToCarrello(@RequestBody ProdottiCarrelloRequest req) {
	


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			prodS.addCarrello(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listpbyidcarrello")
	public ResponseList<ProdottoDTO> listByIdCarrello(@RequestBody ProdottiCarrelloRequest req){
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		r.setRc(true);
		try {
			r.setDati(prodS.listByIdcarrello(req));
		}catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@PostMapping("/deletepbyid")
	public ResponseBase deletepbyid(@RequestBody ProdottiCarrelloRequest req) {
	


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			prodS.deleteProdByIdInCarrello(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@PostMapping("/deletepincarrello")
	public ResponseBase deleteprodottoallinc(@RequestBody ProdottiCarrelloRequest req) {
	


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			prodS.deleteAllProdInCarrello(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

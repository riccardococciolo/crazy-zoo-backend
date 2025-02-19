package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.ProdottoRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.services.interfaces.ProdottoServices;

@RestController
@RequestMapping("/rest/prodotto")
@CrossOrigin(origins = "${url_api}")
public class ProdottoController {
	
	@Autowired
	ProdottoServices proS;
	
	@Autowired
	Logger log;
	
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBase> create(@ModelAttribute ProdottoRequest req) {
		log.debug("Inizio creazione prodotto: {}", req.getTitolo());


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			proS.create(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return ResponseEntity.ok(r);
	}
	
	@PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBase> update(@ModelAttribute ProdottoRequest req) {
		log.debug("Inizio update prodotto: {}", req.getTitolo());


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			proS.update(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return ResponseEntity.ok(r);
	}
	
	@PostMapping(value = "/delete", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBase> delete(@ModelAttribute ProdottoRequest req) {
		log.debug("Inizio update prodotto: {}", req.getTitolo());


		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			proS.delete(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return ResponseEntity.ok(r);
	}
	
    @GetMapping("/listbyfilter")
    public ResponseEntity<Page<ProdottoDTO>> list(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String titolo,
            @RequestParam(required = false) Double prezzoMin,
            @RequestParam(required = false) Double prezzoMax,
            @RequestParam(required = false) Integer quantita,
            @RequestParam(required = false) String nomeAnimale,
            @RequestParam(required = false) String nomeTipologia,
            @RequestParam(required = false) String nomeMarca,
            @RequestParam(required = false) String descrizione,
            Pageable pageable) {

        Page<ProdottoDTO> page = proS.list(id, titolo, prezzoMin, prezzoMax, quantita, nomeAnimale, nomeTipologia, nomeMarca, descrizione, pageable);
        return ResponseEntity.ok(page);
    }
}

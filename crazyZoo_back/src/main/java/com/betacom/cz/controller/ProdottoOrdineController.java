package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.request.ProdottoOrdineRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.ProdottoOrdineServices;

@RestController
@RequestMapping("/rest/prodottoordine")
@CrossOrigin(origins = "${url_api}")
public class ProdottoOrdineController {

    @Autowired
    ProdottoOrdineServices prodottoOrdineS;
    
    @Autowired
    Logger log;

    @PostMapping("/addprodottotoordine")
    public ResponseBase aggiungiProdottoAOrdine(@RequestBody ProdottoOrdineRequest reqPO) {
    	
        ResponseBase r = new ResponseBase();
        r.setRc(true);

        try {
        	prodottoOrdineS.aggiungiProdottoAOrdine(reqPO);
        } catch (Exception e) {
			log.error(e.getMessage());
			
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }
    
    @PostMapping("/deleteall")
    public ResponseBase removeAll(@RequestBody ProdottoOrdineRequest reqPO) {
    	
        ResponseBase r = new ResponseBase();
        r.setRc(true);
        
        try {
        	prodottoOrdineS.removeAll(reqPO);
        } catch (Exception e) {
			log.error(e.getMessage());

            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }
    
    @PostMapping("/deletebyid")
    public ResponseBase removeById(@RequestBody ProdottoOrdineRequest reqPO) {
    	
        ResponseBase r = new ResponseBase();
        r.setRc(true);

        try {
        	prodottoOrdineS.removeById(reqPO);
        } catch (Exception e) {
			log.error(e.getMessage());
        	
            r.setMsg(e.getMessage());
            r.setRc(false);
        }
        return r;
    }

}
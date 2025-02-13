package com.betacom.cz.controller;

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
    private ProdottoOrdineServices prodottoOrdineS;

    @PostMapping("/addprodottotoordine")
    public ResponseBase aggiungiProdottoAOrdine(@RequestBody ProdottoOrdineRequest reqPO) {
        ResponseBase r = new ResponseBase();
        try {
        	prodottoOrdineS.aggiungiProdottoAOrdine(reqPO);
            r.setRc(true);
            r.setMsg("Prodotto aggiunto all'ordine con successo.");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }
        return r;
    }
    
    @PostMapping("/deleteall")
    public ResponseBase removeAll(@RequestBody ProdottoOrdineRequest reqPO) {
        ResponseBase r = new ResponseBase();
        try {
        	prodottoOrdineS.removeAll(reqPO);
            r.setRc(true);
            r.setMsg("Prodotti rimosso dall'ordine con successo.");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }
        return r;
    }
    
    @PostMapping("/deletebyid")
    public ResponseBase removeById(@RequestBody ProdottoOrdineRequest reqPO) {
        ResponseBase r = new ResponseBase();
        try {
        	prodottoOrdineS.removeById(reqPO);
            r.setRc(true);
            r.setMsg("Prodotto rimosso dall'ordine con successo.");
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }
        return r;
    }

}
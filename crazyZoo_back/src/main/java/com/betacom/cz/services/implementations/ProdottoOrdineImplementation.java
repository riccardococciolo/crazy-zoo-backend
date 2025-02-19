package com.betacom.cz.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.models.Ordine;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.repositories.IOrdineRepository;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.request.ProdottoOrdineRequest;
import com.betacom.cz.services.interfaces.ProdottoOrdineServices;
import jakarta.transaction.Transactional;

@Service
public class ProdottoOrdineImplementation implements ProdottoOrdineServices{
	
	@Autowired
	IProdottoRepository prodottoR;
	
	@Autowired
	IOrdineRepository ordineR;
	
	@Autowired
	Logger log;

//
//	@Override
//	@Transactional
//	public void aggiungiProdottoAOrdine(ProdottoOrdineRequest reqPO) {
//		
//		Optional<Prodotto> prodotto = prodottoR.findById(reqPO.getProdottoID());
//		if(prodotto.isEmpty()) {
//			throw new NoSuchElementException("Prodotto non trovato");
//		}
//		
//		Optional<Ordine> ordine = ordineR.findById(reqPO.getOrdineID());
//		if(ordine.isEmpty()) {
//			throw new NoSuchElementException("Ordine non trovato");
//		}
//		
//		Prodotto prod = prodotto.get();
//		
//		Ordine ord = ordine.get();
//		
//		prod.getOrdini().add(ord);
//		ord.getProdotti().add(prod);
//		
//		prodottoR.save(prod);
//		ordineR.save(ord);
//		
//		
//		
//		
//		
////		ordine.get().getProdotti().add(p.get());
////		p.get().getOrdini().add(o.get());
////		prodottoR.save(p.get());
////		ordineR.save(o.get());		
//	}
	
	@Override
	@Transactional
	public void aggiungiProdottoAOrdine(ProdottoOrdineRequest reqPO) {
	    
	    log.debug("Inizio metodo aggiungiProdottoAOrdine con ProdottoID: {} e OrdineID: {}", reqPO.getProdottoID(), reqPO.getOrdineID());

	    Optional<Prodotto> prodottoOpt = prodottoR.findById(reqPO.getProdottoID());
	    if (prodottoOpt.isEmpty()) {
	        log.error("Prodotto con ID {} non trovato", reqPO.getProdottoID());
	        throw new NoSuchElementException("Prodotto non trovato");
	    }
	    
	    Optional<Ordine> ordineOpt = ordineR.findById(reqPO.getOrdineID());
	    if (ordineOpt.isEmpty()) {
	        log.error("Ordine con ID {} non trovato", reqPO.getOrdineID());
	        throw new NoSuchElementException("Ordine non trovato");
	    }

	    Prodotto prodotto = prodottoOpt.get();
	    Ordine ordine = ordineOpt.get();

	    log.debug("Prodotto trovato: ID = {}, Nome = {}", prodotto.getId(), prodotto.getTitolo());
	    log.debug("Ordine trovato: ID = {}", ordine.getId());

	    // Assicuriamoci che le liste non siano null
	    if (ordine.getProdotti() == null) {
	        log.warn("Lista prodotti in Ordine ID {} è null, inizializzazione in corso...", ordine.getId());
	        ordine.setProdotti(new ArrayList<>());
	    }

	    if (prodotto.getOrdini() == null) {
	        log.warn("Lista ordini in Prodotto ID {} è null, inizializzazione in corso...", prodotto.getId());
	        prodotto.setOrdini(new ArrayList<>());
	    }

	    // Aggiunta del prodotto all'ordine
	    ordine.getProdotti().add(prodotto);
	    prodotto.getOrdini().add(ordine);

	    log.debug("Aggiunto Prodotto ID {} all'Ordine ID {}", prodotto.getId(), ordine.getId());

	    // Salvataggio aggiornamenti
	    ordineR.save(ordine);
	    prodottoR.save(prodotto);

	    log.info("Prodotto ID {} aggiunto con successo all'Ordine ID {}", prodotto.getId(), ordine.getId());
	}




	@Override
	public void removeAll(ProdottoOrdineRequest reqPO) {
		
		Optional<Ordine> o = ordineR.findById(reqPO.getOrdineID());
		if(o.isEmpty()) {
			throw new NoSuchElementException("Ordine non trovato");
		}
		
		for(Prodotto p : o.get().getProdotti()) {
			p.getOrdini().remove(o.get());
		}
		
		o.get().getProdotti().clear();
		
		prodottoR.saveAll(o.get().getProdotti());
		
		ordineR.save(o.get());
		
		
	}

	@Override
	public List<Prodotto> listaProdottiInOrdine(ProdottoOrdineRequest reqPO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(ProdottoOrdineRequest reqPO) {
		
		Optional<Ordine> o = ordineR.findById(reqPO.getOrdineID());
		if(o.isEmpty()) {
			throw new NoSuchElementException("Ordine non trovato");
		}
		
		Optional<Prodotto> p = prodottoR.findById(reqPO.getProdottoID());
		if(p.isEmpty()) {
			throw new NoSuchElementException("prodotto non trovato");
		}
		
		o.get().getProdotti().remove(p.get());
		
		p.get().getOrdini().remove(o.get());
		
		ordineR.save(o.get());
		
		prodottoR.save(p.get());
		
		
		
	}

}

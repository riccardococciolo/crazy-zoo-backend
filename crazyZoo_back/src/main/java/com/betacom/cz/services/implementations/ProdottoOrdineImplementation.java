package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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


	@Override
	@Transactional
	public void aggiungiProdottoAOrdine(ProdottoOrdineRequest reqPO) {
		
		Optional<Prodotto> p = prodottoR.findById(reqPO.getProdottoID());
		if(p.isEmpty()) {
			throw new NoSuchElementException("Prodotto non trovato");
		}
		
		Optional<Ordine> o = ordineR.findById(reqPO.getOrdineID());
		if(o.isEmpty()) {
			throw new NoSuchElementException("Ordine non trovato");
		}
		
	
		
		
		
		o.get().getProdotti().add(p.get());
		p.get().getOrdini().add(o.get());
		prodottoR.save(p.get());
		ordineR.save(o.get());		
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

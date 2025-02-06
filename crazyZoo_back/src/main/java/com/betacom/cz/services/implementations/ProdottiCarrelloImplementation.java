package com.betacom.cz.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.cz.models.Carrello;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.repositories.ICarrelloRepository;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.request.ProdottiCarrelloRequest;
import com.betacom.cz.services.interfaces.ProdottiCarrelloServices;

@Service
public class ProdottiCarrelloImplementation implements ProdottiCarrelloServices{

	
	@Autowired
	IProdottoRepository prodR;
	@Autowired
	ICarrelloRepository carR;
	@Override
	public void addCarrello(ProdottiCarrelloRequest req) throws Exception {
		Optional<Prodotto> prod = prodR.findById(req.getId_prodotti());
		if(prod.isEmpty()) {
			throw new Exception("Prodotto non trovato");
		}
		Optional<Carrello> carrello = carR.findById(req.getId_carrello());
		if(carrello.isEmpty()) {
			throw new Exception("Carrello non trovato");
		}
		carrello.get().getProdotti().add(prod.get());
		prod.get().getCarrelli().add(carrello.get());
		prodR.save(prod.get());
		carR.save(carrello.get());
		
	}

}

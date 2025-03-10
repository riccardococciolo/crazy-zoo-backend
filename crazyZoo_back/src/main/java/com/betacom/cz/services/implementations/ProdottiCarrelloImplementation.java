package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.models.Carrello;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.repositories.ICarrelloRepository;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.request.ProdottiCarrelloRequest;
import com.betacom.cz.services.interfaces.ProdottiCarrelloServices;

import static com.betacom.cz.utils.Utilities.mapToProdottoDTOList;

import jakarta.transaction.Transactional;

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
	
	@Override
	@Transactional
	public void deleteAllProdInCarrello(ProdottiCarrelloRequest req) throws Exception {
		
		Optional<Carrello> carrelloOpt = carR.findById(req.getId_carrello());
		
        if (carrelloOpt.isEmpty()) {
            throw new Exception("Carrello non trovato");
        }

        Carrello carrello = carrelloOpt.get();

        for (Prodotto prodotto : carrello.getProdotti()) {
            prodotto.getCarrelli().remove(carrello);
        }

        carrello.getProdotti().clear();

        prodR.saveAll(carrello.getProdotti());

        carR.save(carrello);
		
	}
	
	
	@Override
	@Transactional
	public void deleteProdByIdInCarrello(ProdottiCarrelloRequest req) throws Exception {
		
	    Optional<Prodotto> prodOpt = prodR.findById(req.getId_prodotti());
	    
	    if (prodOpt.isEmpty()) {
	        throw new Exception("Prodotto non trovato");
	    }

	    Optional<Carrello> carrelloOpt = carR.findById(req.getId_carrello());
	    
	    if (carrelloOpt.isEmpty()) {
	        throw new Exception("Carrello non trovato");
	    }

	    Prodotto prod = prodOpt.get();
	    Carrello carrello = carrelloOpt.get();

	    //Rimuoviamo la prima istanza
	    boolean removed = carrello.getProdotti().remove(prod);

	    if (!removed) {
	        throw new Exception("Prodotto non trovato nel carrello");
	    }

	    carR.save(carrello);
	}

	@Override
	public List<ProdottoDTO> listByIdcarrello(ProdottiCarrelloRequest req) throws Exception {
		
		Optional<Carrello> carrello = carR.findById(req.getId_carrello());
		
		if(carrello.isEmpty()) {
			throw new Exception("Carrello non trovato");
		} 
		
		List<Prodotto> lP = carrello.get().getProdotti();
		return mapToProdottoDTOList(lP);
	}

}

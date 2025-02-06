package com.betacom.cz.services.implementations;

import static com.betacom.cz.utils.Utilities.buildImmagineDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.dto.TipologiaDTO;
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

        // Rimuovi il carrello dalla lista di carrelli di ciascun prodotto
        for (Prodotto prodotto : carrello.getProdotti()) {
            prodotto.getCarrelli().remove(carrello);
        }

        // Svuota la lista dei prodotti nel carrello
        carrello.getProdotti().clear();

        // Salva i prodotti aggiornati
        prodR.saveAll(carrello.getProdotti());

        // Salva il carrello aggiornato
        carR.save(carrello);
		
	}
	@Override
	@Transactional
	public void deleteProdByIdInCarrello(ProdottiCarrelloRequest req) throws Exception {
		Optional<Prodotto> prod = prodR.findById(req.getId_prodotti());
		if(prod.isEmpty()) {
			throw new Exception("Prodotto non trovato");
		}
		Optional<Carrello> carrello = carR.findById(req.getId_carrello());
		if(carrello.isEmpty()) {
			throw new Exception("Carrello non trovato");
		}
		prod.get().getCarrelli().remove(carrello.get());
		carrello.get().getProdotti().remove(prod.get());
		carR.save(carrello.get());
		prodR.save(prod.get());
		
		
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

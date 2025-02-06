package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.OrdineDTO;
import com.betacom.cz.models.Carrello;
import com.betacom.cz.models.Ordine;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.ICarrelloRepository;
import com.betacom.cz.repositories.IOrdineRepository;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.OrdineRequest;
import com.betacom.cz.services.interfaces.OrdineServices;
import jakarta.transaction.Transactional;

@Service
public class OrdineImplementation implements OrdineServices{
	
	@Autowired
	IOrdineRepository ordineR;
	
	@Autowired
	ICarrelloRepository carrelloR;
	
	@Autowired
	IUtenteRepository utenteR;
	
	@Autowired
	Logger log;

	@Override
	@Transactional
	public void create(OrdineRequest req) throws Exception {
		
		Optional<Utente> u = utenteR.findById(req.getUtenteID());
		if(u.isEmpty()) {
			log.error("Utente non esiste");
			throw new Exception("Utente non esiste");
		}
	    Utente utente = u.get();
	    
	    Carrello carrello = utente.getCarrello();
		
		Ordine ordine = new Ordine();
		
		ordine.setCarrello(carrello);

		ordine.setUtente(utente);
		
		ordineR.save(ordine);
		
	    log.info("Ordine creato con successo per l'utente {} con il carrello {}", utente.getId(), carrello.getId());
		
	}

	@Override
	@Transactional
	public void delete(OrdineRequest req) throws Exception {
		
	    Optional<Ordine> optionalOrdine = ordineR.findById(req.getId());
	    
	    if (optionalOrdine.isEmpty()) {
	        log.error("Ordine con ID '{}' non esiste", req.getId());
	        throw new Exception("Ordine non trovato");
	    }

	    Ordine ordine = optionalOrdine.get();
	    
	    // 1️⃣ Rimuovere il legame tra Ordine e Prodotti nella relazione @ManyToMany
	    if (!ordine.getProdotti().isEmpty()) {
	        for (Prodotto prodotto : ordine.getProdotti()) {
	            prodotto.getOrdini().remove(ordine);
	        }
	        ordine.getProdotti().clear();
	    }
	    
	    // 2️⃣ Rimuovere il legame tra Carrello e Ordine
	    Carrello carrello = ordine.getCarrello();
	    if (carrello != null) {
	        carrello.setOrdini(null);
	        carrelloR.save(carrello);
	    }
	    
	    // 3️⃣ Rimuovere il legame tra Utente e Ordine
	    Utente utente = ordine.getUtente();
	    if (utente != null) {
	        utente.getOrdini().remove(ordine);
	    }
	    
	    // 4️⃣ Eliminare l'ordine
	    ordineR.delete(ordine);

	    log.info("Ordine con ID '{}' eliminato con successo.", req.getId());


	}

	@Override
	public List<OrdineDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdineDTO listById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.OrdineDTO;
import com.betacom.cz.models.Carrello;
import com.betacom.cz.models.Ordine;
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
	public void delete(OrdineRequest req) {
		// TODO Auto-generated method stub
		
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

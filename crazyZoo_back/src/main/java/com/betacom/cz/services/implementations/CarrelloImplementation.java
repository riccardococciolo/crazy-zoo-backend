package com.betacom.cz.services.implementations;

import static com.betacom.cz.utils.Utilities.mapToProdottoDTOList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.CarrelloDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.models.Carrello;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.ICarrelloRepository;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.CarrelloRequest;
import com.betacom.cz.services.interfaces.CarrelloServices;
import jakarta.transaction.Transactional;

@Service
public class CarrelloImplementation implements CarrelloServices{
	@Autowired
	ICarrelloRepository carrR;
	@Autowired
	IUtenteRepository uttR;
	
	@Autowired
	IProdottoRepository prodottoR;
	
	@Autowired
	Logger log;
	

	@Override
	public void create(CarrelloRequest req) throws Exception {
	    Optional<Utente> ut = uttR.findById(req.getUtenteID());
	    
	    if (ut.isEmpty()) {
	        throw new Exception("Utente non trovato");
	    }

	    // Controllo se il carrello esiste già per quell'utente
	    Optional<Carrello> existingCarrello = carrR.findByUtenteId(req.getUtenteID());
	    if (existingCarrello.isPresent()) {
	        throw new Exception("Carrello già esistente per l'utente con ID: " + req.getUtenteID());
	    }

	    // Creazione di un nuovo carrello se non esiste
	    Carrello car = new Carrello();
	    car.setUtente(ut.get());
	    carrR.save(car);
	}


//	@Override
//	public void update(CarrelloRequest req) throws Exception {
//		 Optional<Utente> ut = uttR.findById(req.getUtenteID());
//		    
//		    if (ut.isEmpty()) {
//		        throw new Exception("Utente non trovato");
//		    }
//		    Optional<Carrello> existingCarrello = carrR.findById(req.getId());
//		    if (existingCarrello.isEmpty()) {
//		        throw new Exception("Carrello non esistente");
//		    }
//		    Optional<Carrello> existinCarrello = carrR.findByUtenteId(req.getUtenteID());
//		    if (existinCarrello.isPresent()) {
//		        throw new Exception("Carrello già esistente per l'utente con ID: " + req.getUtenteID());
//		    }
//		    existingCarrello.get().setUtente(ut.get());
//		    carrR.save(existingCarrello.get());
//		    
//		
//	}

//	@Override
//	public void delete(CarrelloRequest req) throws Exception {
//		// TODO Auto-generated method stub
//		Optional<Carrello> existingCarrello = carrR.findById(req.getId());
//		 if (existingCarrello.isEmpty()) {
//		        throw new Exception("Carrello non esistente");
//	    }
//		 carrR.delete(existingCarrello.get());
//		
//	}
	
	@Override
	@Transactional
	public void delete(CarrelloRequest req) throws Exception {
	    Optional<Carrello> existingCarrello = carrR.findById(req.getId());

	    if (existingCarrello.isEmpty()) {
	        throw new Exception("Carrello non esistente");
	    }

	    Carrello carrello = existingCarrello.get();

	    // 1️⃣ Scollega il carrello dall'utente
	    if (carrello.getUtente() != null) {
	        carrello.getUtente().setCarrello(null);
	        uttR.save(carrello.getUtente());  // Salva l'utente aggiornato
	    }

	    // 2️⃣ Scollega il carrello dai prodotti
	    if (carrello.getProdotti() != null && !carrello.getProdotti().isEmpty()) {
	        for (Prodotto p : carrello.getProdotti()) {
	            p.getCarrelli().remove(carrello);  // Rimuove il carrello dalla lista dei prodotti
	        }
	        prodottoR.saveAll(carrello.getProdotti());
	        carrello.getProdotti().clear(); // Rimuove tutti i prodotti dalla lista del carrello
	    }

	    // 3️⃣ Ora si può eliminare il carrello senza errori
	    carrR.delete(carrello);

	    log.info("Carrello con ID '{}' eliminato con successo.", carrello.getId());
	}


	@Override
	@Transactional
	public List<CarrelloDTO> listAll() {
		
		List<Carrello> carrelloList = carrR.findAll(); 
		
	    if (carrelloList.isEmpty()) {
	        log.error("Nessun carrello trovato nel database.");
	        throw new IllegalStateException("Nessun carrello disponibile.");
	    }

	    log.info("Trovati {} carrelli nel database.", carrelloList.size());
	    
	    return carrelloList.stream()
	                 .map(c -> new CarrelloDTO(
	                		 c.getId(), 
	                		 (new UtenteDTO(
	                				 c.getId(), 
	                        		 c.getUtente().getNome(), 
	                        		 c.getUtente().getCognome(), 
	                        		 c.getUtente().getUsername(),
	                        		 c.getUtente().getEmail(), 
	                        		 c.getUtente().getCellulare(), 
	                        		 c.getUtente().getRuolo()+"" ))))
	                 .collect(Collectors.toList());
	}
	
	@Override
	public List<ProdottoDTO> listProdotto(Integer id_utente) throws Exception {
		Optional<Utente> utente = uttR.findById(id_utente);
		Optional<Carrello> carrello = carrR.findById(utente.get().getCarrello().getId());
		if(carrello.isEmpty()) {
			throw new Exception("Carrello non trovato");
		} 
		List<Prodotto> lP = carrello.get().getProdotti();
		return mapToProdottoDTOList(lP);
	}


	

}

package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.betacom.cz.utils.Utilities.mapToProdottoDTOList;
import com.betacom.cz.dto.CarrelloDTO;
import com.betacom.cz.dto.IndirizzoDTO;
import com.betacom.cz.dto.OrdineDTO;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.models.Carrello;
import com.betacom.cz.models.Indirizzo;
import com.betacom.cz.models.Ordine;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.ICarrelloRepository;
import com.betacom.cz.repositories.IOrdineRepository;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.OrdineRequest;
import com.betacom.cz.request.ProdottoOrdineRequest;
import com.betacom.cz.services.interfaces.OrdineServices;
import com.betacom.cz.services.interfaces.ProdottoOrdineServices;

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
	
	@Autowired
	ProdottoOrdineServices prodordS;
	
	@Autowired
	IProdottoRepository prodottoR;

	@Override
	@Transactional
	public void create(OrdineRequest req) throws Exception {
	    
	    Optional<Utente> u = utenteR.findById(req.getUtenteID());
	    if (u.isEmpty()) {
	        log.error("Utente non esiste");
	        throw new Exception("Utente non esiste");
	    }
	    Utente utente = u.get();
	    
	    Carrello carrello = utente.getCarrello();

	    // Controllo se il carrello è vuoto
	    if (carrello.getProdotti().isEmpty()) {
	        log.warn("Il carrello dell'utente {} è vuoto, impossibile creare l'ordine", utente.getId());
	        throw new Exception("Il carrello è vuoto");
	    }
	     Double tot = 0.00;
	    // Controllo e aggiornamento della quantità dei prodotti
	    for (Prodotto p : carrello.getProdotti()) {
        if (p.getQuantita() > 0) {
        	tot += p.getPrezzo();
            p.setQuantita(p.getQuantita() - 1);
	        } else {
	            log.error("Prodotto {} non disponibile in magazzino", p.getTitolo());
	            throw new Exception("Prodotto " + p.getTitolo() + " non disponibile in magazzino");
	        }
	    }

	    // Salvare l'aggiornamento delle quantità nel DB
	    prodottoR.saveAll(carrello.getProdotti());

	    // Creazione dell'ordine
	    Ordine ordine = new Ordine();
	    ordine.setTotale(tot);
	    ordine.setCarrello(carrello);
	    ordine.setUtente(utente);

	    // Salvataggio dell'ordine nel database
	    ordine = ordineR.save(ordine);
	    Integer id = ordine.getId(); // ID dell'ordine salvato

	    // Associare ogni prodotto all'ordine
	    for (Prodotto p : carrello.getProdotti()) {
	        prodordS.aggiungiProdottoAOrdine(new ProdottoOrdineRequest(p.getId(), id));
	    }

	    // Rimuovere i prodotti dal carrello dopo la creazione dell'ordine
	    carrello.getProdotti().clear();
	    carrelloR.save(carrello); // Assicurarsi che l'aggiornamento sia persistente

	    log.info("Ordine creato con successo. ID Ordine: {}, Utente: {}, Carrello: {}", id, utente.getId(), carrello.getId());
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
	    List<Ordine> lO = ordineR.findAll();
	    
	    return lO.stream().map(m -> new OrdineDTO(
	        m.getId(),
	        new CarrelloDTO(m.getCarrello().getId(),
	            new UtenteDTO(m.getUtente().getId(), 
	                m.getUtente().getNome(), 
	                m.getUtente().getCognome(), 
	                m.getUtente().getUsername(),
	                m.getUtente().getEmail(), 
	                m.getUtente().getCellulare(), 
	                m.getUtente().getRuolo()+"")),
	        new UtenteDTO(
	            m.getUtente().getId(), 
	            m.getUtente().getNome(), 
	            m.getUtente().getCognome(), 
	            m.getUtente().getUsername(),
	            m.getUtente().getEmail(), 
	            m.getUtente().getCellulare(), 
	            m.getUtente().getRuolo()+"",
	            new IndirizzoDTO(m.getUtente().getIndirizzo().getVia(), 
	                             m.getUtente().getIndirizzo().getCivico(),
	                             m.getUtente().getIndirizzo().getCap(), 
	                             m.getUtente().getIndirizzo().getCitta())),
	        mapToProdottoDTOList(m.getProdotti()),
	        m.getTotale()
	    )).collect(Collectors.toList());
	}




	@Override
	public OrdineDTO listById(Integer id) throws Exception {
		Optional<Ordine> m = ordineR.findById(id);
		
	    if (m.isEmpty()) {
	        log.error("Nessuna ordine trovata nel database.");
	        throw new Exception("Nessuna ordine disponibile.");
	    }
	    
	    return new OrdineDTO(
	    		m.get().getId(),
	    		new CarrelloDTO(m.get().getCarrello().getId(),
	    				new UtenteDTO( m.get().getId(), 
	    						m.get().getUtente().getNome(), 
	    						m.get().getUtente().getCognome(), 
	    						m.get().getUtente().getUsername(),
	    						m.get().getUtente().getEmail(), 
	    						m.get().getUtente().getCellulare(), 
	    						m.get().getUtente().getRuolo()+"")),
	    		new UtenteDTO(	    		
	    				m.get().getId(), 
	    				m.get().getUtente().getNome(), 
	    				m.get().getUtente().getCognome(), 
	    				m.get().getUtente().getUsername(),
	    				m.get().getUtente().getEmail(), 
	    				m.get().getUtente().getCellulare(), 
	    				m.get().getUtente().getRuolo()+"",
	    				new IndirizzoDTO(m.get().getUtente().getIndirizzo().getVia(), 
	    								 m.get().getUtente().getIndirizzo().getCivico(),
	    								 m.get().getUtente().getIndirizzo().getCap(), 
	    								 m.get().getUtente().getIndirizzo().getCitta())),
	    		mapToProdottoDTOList(m.get().getProdotti()),m.get().getTotale());
	}



	@Override
	public List<OrdineDTO> listByIdUtente(Integer id) throws Exception {
		
		List<Ordine> lO = ordineR.findByUtente_Id(id);
		
	    if (lO.isEmpty()) {
	        log.error("Nessuna ordine trovato nel database.");
	        throw new Exception("Nessuna ordine disponibile.");
	    }
		
		return lO.stream().map(m -> new OrdineDTO(
		        m.getId(),
		        new CarrelloDTO(m.getCarrello().getId(),
		            new UtenteDTO(m.getUtente().getId(), 
		                m.getUtente().getNome(), 
		                m.getUtente().getCognome(), 
		                m.getUtente().getUsername(),
		                m.getUtente().getEmail(), 
		                m.getUtente().getCellulare(), 
		                m.getUtente().getRuolo()+"")),
		        new UtenteDTO(
		            m.getUtente().getId(), 
		            m.getUtente().getNome(), 
		            m.getUtente().getCognome(), 
		            m.getUtente().getUsername(),
		            m.getUtente().getEmail(), 
		            m.getUtente().getCellulare(), 
		            m.getUtente().getRuolo()+"",
		            new IndirizzoDTO(m.getUtente().getIndirizzo().getVia(), 
		                             m.getUtente().getIndirizzo().getCivico(),
		                             m.getUtente().getIndirizzo().getCap(), 
		                             m.getUtente().getIndirizzo().getCitta())),
		        mapToProdottoDTOList(m.getProdotti()),
		        m.getTotale()
		    )).collect(Collectors.toList());
		
	}
}
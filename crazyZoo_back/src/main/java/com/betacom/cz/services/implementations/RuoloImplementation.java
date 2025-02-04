package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.RuoloDTO;
import com.betacom.cz.models.Ruolo;
import com.betacom.cz.repositories.IRuoloRepository;
import com.betacom.cz.request.RuoloRequest;
import com.betacom.cz.services.interfaces.RuoloServices;
import jakarta.transaction.Transactional;

@Service
public class RuoloImplementation implements RuoloServices {
	
	@Autowired
	IRuoloRepository ruoloR;
	
	@Autowired
	Logger log;

	@Override
	@Transactional
	public void create(RuoloRequest req) throws Exception {
		
		Optional<Ruolo> r = ruoloR.findByNome(req.getNome());
		
		if (r.isPresent())
			throw new Exception("Ruolo esiste già");
		
		Ruolo ruolo = new Ruolo();		
		ruolo.setNome(req.getNome());		
		ruoloR.save(ruolo);

	    log.info("Ruolo '{}' creato con successo.", req.getNome());
		
	}

	@Override
	@Transactional
	public void update(RuoloRequest req) throws Exception {
		
	    Optional<Ruolo> r = ruoloR.findById(req.getId());
	    
	    if (r.isEmpty()) {
	        throw new Exception("Marca non trovata");
	    }

	    boolean isUpdated = false;
	    
	    Ruolo ruolo = r.get();
	    
	    if (req.getNome() != null && !req.getNome().trim().isEmpty()) {
	        String nuovoRuolo = req.getNome().trim();

	        Optional<Ruolo> rC = ruoloR.findByNome(nuovoRuolo);
	        if (rC.isPresent() && !rC.get().getId().equals(ruolo.getId())) {
	            throw new Exception("Esiste già un ruolo con questo nome.");
	        }

	        ruolo.setNome(nuovoRuolo);
	        isUpdated = true;
	    }
	    
	    if (isUpdated) {
	        ruoloR.save(ruolo);
	        log.info("Ruolo con ID '{}' aggiornata con successo a '{}'.", ruolo.getId(), ruolo.getNome());
	    } else {
	        log.warn("Nessuna modifica effettuata per il ruolo con ID '{}'", ruolo.getId());
	    }
		
	}

	@Override
	@Transactional
	public void delete(RuoloRequest req) throws Exception {
		
	    Optional<Ruolo> oRuolo = ruoloR.findById(req.getId());

	    if (oRuolo.isEmpty()) {
	        log.error("Tentativo di eliminare ruolo inesistente");
	        throw new Exception("Ruolo non trovato");
	    }

	    Ruolo ruolo = oRuolo.get();
	        
	    ruoloR.delete(ruolo);

	    log.info("Ruolo '{}' eliminata con successo.", ruolo.getNome());
		
	}

	@Override
	public List<RuoloDTO> listAll() {
		
		List<Ruolo> ruoli = ruoloR.findAll(); 
		
	    if (ruoli.isEmpty()) {
	        log.error("Nessun ruolo trovata nel database.");
	        throw new IllegalStateException("Nessun ruolo disponibile.");
	    }

	    log.info("Trovati {} ruoli nel database.", ruoli.size());
	    
	    return ruoli.stream()
	                 .map(r -> new RuoloDTO(r.getId(), r.getNome()))
	                 .collect(Collectors.toList());
	}

}

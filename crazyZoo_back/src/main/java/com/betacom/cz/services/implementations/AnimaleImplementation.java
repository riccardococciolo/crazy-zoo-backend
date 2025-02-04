package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.models.Animale;
import com.betacom.cz.models.Marca;
import com.betacom.cz.repositories.IAnimaleRepository;
import com.betacom.cz.repositories.IMarcaRepository;
import com.betacom.cz.request.AnimaleRequest;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.services.interfaces.AnimaleServices;

import jakarta.transaction.Transactional;

@Service
public class AnimaleImplementation implements AnimaleServices {
	
	@Autowired
	IAnimaleRepository animaleR;
	
	@Autowired
	Logger log;

	@Override
	@Transactional
	public void create(AnimaleRequest req) throws Exception {

		Optional<Animale> animale = animaleR.findByNomeAnimale(req.getNome());
							
		if (animale.isPresent())
			throw new Exception("Animale esiste già");
		
		Animale animaleObj = new Animale();		
		animaleObj.setNomeAnimale(req.getNome());		
		animaleR.save(animaleObj);

	    log.info("Animale '{}' creata con successo.", req.getNome());
	}

	@Override
	@Transactional
	public void update(AnimaleRequest req) throws Exception {
	    
	    Optional<Animale> animale = animaleR.findById(req.getId());
	    
	    if (animale.isEmpty()) {
	        throw new Exception("Animale non trovato");
	    }

	    boolean isUpdated = false;
	    
	    Animale animaleObj = animale.get();
	    
	    if (req.getNome() != null && !req.getNome().trim().isEmpty()) {
	        String nuovoNome = req.getNome().trim();

	        Optional<Animale> animC = animaleR.findByNomeAnimale(nuovoNome);
	        if (animC.isPresent() && !animC.get().getId().equals(animaleObj.getId())) {
	            throw new Exception("Esiste già un animale con questo nome.");
	        }

	        animaleObj.setNomeAnimale(nuovoNome);
	        isUpdated = true;
	    }
	    
	    if (isUpdated) {
	        animaleR.save(animaleObj);
	        log.info("Animale con ID '{}' aggiornato con successo a '{}'.", animaleObj.getId(), animaleObj.getNomeAnimale());
	    } else {
	        log.warn("Nessuna modifica effettuata per l'animale con ID '{}'", animaleObj.getId());
	    }
	}

	@Override
	@Transactional
	public void delete(AnimaleRequest req) throws Exception {

	    Optional<Animale> animaleList = animaleR.findById(req.getId());

	    if (animaleList.isEmpty()) {
	        log.error("Tentativo di eliminare una marca inesistente");
	        throw new Exception("Marca non trovata");
	    }

	    Animale animaleObj = animaleList.get();
	    animaleR.delete(animaleObj);

	    log.info("Animale '{}' eliminato con successo.", animaleObj.getNomeAnimale());
	}

	@Override
	public List<AnimaleDTO> listAll() {
		
		List<Animale> animaleList = animaleR.findAll(); 
		
	    if (animaleList.isEmpty()) {
	        log.error("Nessun animale trovato nel database.");
	        throw new IllegalStateException("Nessun animale disponibile.");
	    }

	    log.info("Trovati {} animali nel database.", animaleList.size());
	    
	    return animaleList.stream()
	                 .map(animaleDTO -> new AnimaleDTO(animaleDTO.getId(), animaleDTO.getNomeAnimale()))
	                 .collect(Collectors.toList());
	}

	@Override
	public AnimaleDTO listByID(Integer id) {
		
		Optional<Animale> animaleList = animaleR.findById(id);
		
	    if (animaleList.isEmpty()) {
	        log.error("Nessun animale trovato nel database.");
	        throw new IllegalStateException("Nessun animale disponibile.");
	    }
	    
	    return new AnimaleDTO(animaleList.get().getId(), animaleList.get().getNomeAnimale());
		
	}
}

package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.models.Marca;
import com.betacom.cz.repositories.IMarcaRepository;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.services.interfaces.MarcaServices;
import jakarta.transaction.Transactional;

@Service
public class MarcaImplementation implements MarcaServices{
	
	@Autowired
	IMarcaRepository marcaR;
	
	@Autowired
	Logger log;

	@Override
	@Transactional
	public void create(MarcaRequest req) {

		String nomeMarca = Optional.ofNullable(req)
	            .map(MarcaRequest::getNomeMarca)
	            .map(String::trim)
	            .orElseThrow(() -> new IllegalArgumentException("Il nome della marca non può essere vuoto."));

	    if (marcaR.findByNomeMarca(nomeMarca).isPresent()) {
	        log.error("Tentativo di inserire marca duplicata: '{}'", nomeMarca);
	        throw new IllegalArgumentException("La marca esiste già.");
	    }

	    Marca marca = new Marca();
	    marca.setNomeMarca(nomeMarca);
	    marcaR.save(marca);

	    log.info("Marca '{}' creata con successo.", marca.getNomeMarca());
	}

	@Override
	@Transactional
	public void updateByName(MarcaRequest req, String nomeMarca) {

		String nomeA = Optional.ofNullable(nomeMarca)
	            .map(String::trim)
	            .orElseThrow(() -> new IllegalArgumentException("Il nome della marca da aggiornare non può essere vuoto."));

	    Marca marca = marcaR.findByNomeMarca(nomeA)
	            .orElseThrow(() -> {
	                log.error("Tentativo di aggiornare una marca inesistente: '{}'", nomeA);
	                return new IllegalArgumentException("La marca non esiste.");
	            });

	    String nuovoNome = Optional.ofNullable(req)
	            .map(MarcaRequest::getNomeMarca)
	            .map(String::trim)
	            .orElseThrow(() -> new IllegalArgumentException("Il nuovo nome della marca non può essere vuoto."));

	    if (!nomeA.equals(nuovoNome) && marcaR.findByNomeMarca(nuovoNome).isPresent()) {
	        throw new IllegalArgumentException("Esiste già una marca con questo nome.");
	    }

	    marca.setNomeMarca(nuovoNome);
	    marcaR.save(marca);

	    log.info("Marca '{}' aggiornata con successo a '{}'.", nomeA, marca.getNomeMarca());
	}

	@Override
	@Transactional
	public void deleteByName(String nomeMarca) {

		String nomeE = Optional.ofNullable(nomeMarca)
	            .map(String::trim)
	            .orElseThrow(() -> new IllegalArgumentException("Il nome della marca da eliminare non può essere vuoto."));

	    Marca marca = marcaR.findByNomeMarca(nomeE)
	            .orElseThrow(() -> {
	                log.error("Tentativo di eliminare una marca inesistente: '{}'", nomeE);
	                return new IllegalArgumentException("La marca non esiste.");
	            });

	    marcaR.delete(marca);

	    log.info("Marca '{}' eliminata con successo.", marca.getNomeMarca());
	}

	@Override
	public List<MarcaDTO> listAll() {
		
		List<Marca> marche = marcaR.findAll(); 
		
	    if (marche.isEmpty()) {
	        log.error("Nessuna marca trovata nel database.");
	        throw new IllegalStateException("Nessuna marca disponibile.");
	    }

	    log.info("Trovate {} marche nel database.", marche.size());
	    
	    return marche.stream()
	                 .map(m -> new MarcaDTO(m.getId(), m.getNomeMarca()))
	                 .collect(Collectors.toList());
	}

}

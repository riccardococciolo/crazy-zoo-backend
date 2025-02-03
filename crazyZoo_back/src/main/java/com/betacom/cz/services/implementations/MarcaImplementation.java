package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
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

	    if (nomeMarca.isEmpty()) {
	        throw new IllegalArgumentException("Il nome della marca non può essere vuoto.");
	    }

	    if (marcaR.findByNomeMarca(nomeMarca).isPresent()) {
	        throw new IllegalArgumentException("La marca esiste già.");
	    }

	    Marca marca = new Marca();
	    marca.setNomeMarca(nomeMarca);
	    marcaR.save(marca);
	    
		log.info("Marca '{}' creata con successo", req.getNomeMarca());

	}

	@Override
	@Transactional
	public void updateByName(MarcaRequest req, String nomeMarca) {
		
	    nomeMarca = Optional.ofNullable(nomeMarca)
	            .map(String::trim)
	            .orElseThrow(() -> new IllegalArgumentException("Il nome della marca da aggiornare non può essere vuoto."));

	    Optional<Marca> m = marcaR.findByNomeMarca(nomeMarca);

	    if (m.isEmpty()) {
	        log.error("Tentativo di aggiornare una marca inesistente: '{}'", nomeMarca);
	        throw new IllegalArgumentException("La marca non esiste.");
	    }

	    Marca marca = m.get(); 

	    String nuovoNome = Optional.ofNullable(req)
	            .map(MarcaRequest::getNomeMarca)
	            .map(String::trim)
	            .orElseThrow(() -> new IllegalArgumentException("Il nuovo nome della marca non può essere vuoto."));

	    if (nuovoNome.isEmpty()) {
	        throw new IllegalArgumentException("Il nuovo nome della marca non può essere vuoto.");
	    }

	    if (!nomeMarca.equals(nuovoNome) && marcaR.findByNomeMarca(nuovoNome).isPresent()) {
	        throw new IllegalArgumentException("Esiste già una marca con questo nome.");
	    }

	    marca.setNomeMarca(nuovoNome);
	    marcaR.save(marca);

	    log.info("Marca '{}' aggiornata a '{}'", nomeMarca, nuovoNome);
	}


	@Override
	public void delete(String nomeMarca) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MarcaDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

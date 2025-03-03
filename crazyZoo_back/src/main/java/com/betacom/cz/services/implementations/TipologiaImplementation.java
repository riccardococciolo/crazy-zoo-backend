package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.TipologiaDTO;
import com.betacom.cz.models.Tipologia;
import com.betacom.cz.repositories.ITipologiaRepository;
import com.betacom.cz.request.TipologiaRequest;
import com.betacom.cz.services.interfaces.TipologiaService;

import jakarta.transaction.Transactional;

@Service
public class TipologiaImplementation implements TipologiaService{

	@Autowired
	ITipologiaRepository tipologiaR;

	@Autowired
	Logger log;

	@Override
	@Transactional
	public void create(TipologiaRequest req) throws Exception {

		Optional<Tipologia> tip = tipologiaR.findByNome(req.getNome());
		if(tip.isPresent())
			throw new Exception("Tipologia già esistente " + req.getNome());
		Tipologia t = new Tipologia();
		t.setNome(req.getNome());
		tipologiaR.save(t);

		log.info("Tipologia '{}' creata con successo.", req.getNome());
	}

	@Override
	@Transactional
	public void update(TipologiaRequest req) throws Exception {
		Optional<Tipologia> tip = tipologiaR.findById(req.getId());
		if(tip.isEmpty())
			throw new Exception("Tipologia non trovata " + req.getNome() + " id cercato --> " + req.getId());

		boolean isToUp = false;
		Tipologia tipo = tip.get();
		if (req.getNome() != null && !req.getNome().trim().isEmpty()) {
			String nuovoNome = req.getNome().trim();

			Optional<Tipologia> tipologia = tipologiaR.findByNome(nuovoNome);
			if (tipologia.isPresent() && !tipologia.get().getId().equals(tipo.getId())) {
				throw new Exception("Esiste già una tipologia con questo nome.");
			}
			tipo.setNome(nuovoNome);
			isToUp=true;
		}
		if (isToUp) {
			tipologiaR.save(tipo);
			log.info("Tipologia con ID '{}' aggiornata con successo a '{}'.", tipo.getId(), tipo.getNome());
		} else {
			log.warn("Nessuna modifica effettuata per la tipologia con ID '{}'", tipo.getId());
		}
	}

	@Override
	public void delete(TipologiaRequest req) throws Exception {

		Optional<Tipologia> t = tipologiaR.findById(req.getId());

		if (t.isEmpty()) {
			log.error("Tentativo di eliminare una tipologia inesistente");
			throw new Exception("Tipologia non trovata");
		}

		Tipologia tipo = t.get();
		tipologiaR.delete(tipo);

		log.info("Tipologia '{}' eliminata con successo.", tipo.getNome());

	}

	@Override
	public List<TipologiaDTO> listAll() {

		List<Tipologia> tipologie = tipologiaR.findAll(); 

		if (tipologie.isEmpty()) {
			log.error("Nessuna tipologia trovata nel database.");
			throw new IllegalStateException("Nessuna tipologia disponibile.");
		}

		log.info("Trovate {} marche nel database.", tipologie.size());

		return tipologie.stream()
				.map(m -> new TipologiaDTO(m.getId(), m.getNome()))
				.collect(Collectors.toList());
	}

	@Override
	public TipologiaDTO listByID(Integer id) throws Exception {
		Optional<Tipologia> m = tipologiaR.findById(id);

		if (m.isEmpty()) {
			log.error("Nessuna tipologia trovata nel database.");
			throw new Exception("Nessuna tpologia disponibile.");
		}

		return new TipologiaDTO(m.get().getId(), m.get().getNome());


	}


}

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
	public void create(MarcaRequest req) throws Exception {

		Optional<Marca> m = marcaR.findByNomeMarca(req.getNome());

		if (m.isPresent())
			throw new Exception("Marca esiste già");

		Marca marca = new Marca();		
		marca.setNomeMarca(req.getNome());		
		marcaR.save(marca);

		log.info("Marca '{}' creata con successo.", req.getNome());
	}

	@Override
	@Transactional
	public void update(MarcaRequest req) throws Exception {

		Optional<Marca> m = marcaR.findById(req.getId());

		if (m.isEmpty()) {
			throw new Exception("Marca non trovata");
		}

		boolean isUpdated = false;

		Marca marca = m.get();

		if (req.getNome() != null && !req.getNome().trim().isEmpty()) {
			String nuovoNome = req.getNome().trim();

			Optional<Marca> mC = marcaR.findByNomeMarca(nuovoNome);
			if (mC.isPresent() && !mC.get().getId().equals(marca.getId())) {
				throw new Exception("Esiste già una marca con questo nome.");
			}

			marca.setNomeMarca(nuovoNome);
			isUpdated = true;
		}

		if (isUpdated) {
			marcaR.save(marca);
			log.info("Marca con ID '{}' aggiornata con successo a '{}'.", marca.getId(), marca.getNomeMarca());
		} else {
			log.warn("Nessuna modifica effettuata per la marca con ID '{}'", marca.getId());
		}
	}

	@Override
	@Transactional
	public void delete(MarcaRequest req) throws Exception {

		Optional<Marca> oMarca = marcaR.findById(req.getId());

		if (oMarca.isEmpty()) {
			log.error("Tentativo di eliminare una marca inesistente");
			throw new Exception("Marca non trovata");
		}

		Marca marca = oMarca.get();
		marca.getProdotti().clear();
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

	@Override
	public MarcaDTO listByID(Integer id) {

		Optional<Marca> m = marcaR.findById(id);

		if (m.isEmpty()) {
			log.error("Nessuna marca trovata nel database.");
			throw new IllegalStateException("Nessuna marca disponibile.");
		}

		return new MarcaDTO(m.get().getId(), m.get().getNomeMarca());


	}


}

package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.betacom.cz.dto.IndirizzoDTO;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.models.Indirizzo;
import com.betacom.cz.models.Ruolo;
import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.services.interfaces.UtenteServices;

@Service
public class UtenteImplementation implements UtenteServices{


	@Autowired
	IUtenteRepository userR;


	@Autowired
	Logger log;

	@Autowired
	PasswordEncoder pwdEncoder;

	@Override
	public void create(UtenteRequest req) throws Exception {

		Optional<Utente> userByEmail = userR.findByEmail(req.getEmail());
		Optional<Utente> userByUsername = userR.findByUsername(req.getUsername());
		Optional<Utente> userByCell = userR.findByCellulare(req.getCellulare());

		if (userByEmail.isPresent()) 
			throw new Exception("Email già esitente");
		if(userByUsername.isPresent())
			throw new Exception("Username già esitente");
		if(userByCell.isPresent())
			throw new Exception("Cellulare già esistente");


		Utente u = new Utente();
		u.setNome(req.getNome());
		u.setCognome(req.getCognome());


		u.setUsername(req.getUsername());

		//Crittografia della password
		u.setPassword(pwdEncoder.encode(req.getPassword()));

		u.setCellulare(req.getCellulare());
		u.setEmail(req.getEmail());

		//Imposta CLIENTE default
		Ruolo ruolo = Ruolo.CLIENTE;
		if (req.getRuolo() != null) {
			try {
				ruolo = Ruolo.valueOf(req.getRuolo().toUpperCase()); 
			} catch (IllegalArgumentException e) {
				log.warn("Ruolo specificato non valido, impostato di default a CLIENTE");
			}
		}

		u.setRuolo(ruolo);

		Indirizzo i = new Indirizzo();
		i.setUtente(u);
		i.setVia(req.getVia());
		i.setCivico(req.getCivico());
		i.setCap(req.getCap());
		i.setCitta(req.getCitta());

		u.setIndirizzo(i);

		userR.save(u);
	}


	@Override
	public void delete(UtenteRequest req) throws Exception {

		Optional<Utente> user = userR.findById(req.getId());

		if(user.isEmpty()) {
			log.error("Tentativo di eliminare una utente inesistente");
			throw new Exception("Utente non trovato");		
		}
		Utente u = user.get();
		userR.delete(u);
		log.info("Utente id: '{}', nome: '{}', Username: '{}', Cellulare: '{}', Email:'{}' eliminato con successo.",
				u.getId(),u.getNome(),
				u.getUsername(),u.getCellulare(),
				u.getEmail());


	}

	@Override
	public List<UtenteDTO> listAll() {
		List<Utente> user = userR.findAll(); 

		if (user.isEmpty()) {
			log.error("Nessun utente trovat nel database.");
			throw new IllegalStateException("Nessun Utente disponibile.");
		}

		log.info("Trovati {} utenti nel database.", user.size());

		return user.stream()
				.map(m -> new UtenteDTO(m.getId(), m.getNome(), m.getCognome(), m.getUsername(), m.getEmail(), m.getCellulare(), m.getRuolo()+"" ))
				.collect(Collectors.toList());

	}

	@Override
	public UtenteDTO listByID(Integer id) throws Exception  {

		Optional<Utente> u = userR.findById(id);
		if(u.isEmpty())
			throw new Exception("Nessun user trovato......");
		return new UtenteDTO(u.get().getId(), 
				u.get().getNome(), 
				u.get().getCognome(), 
				u.get().getUsername(),
				u.get().getEmail(), 
				u.get().getCellulare(), 
				u.get().getRuolo()+"",
				new IndirizzoDTO(u.get().getIndirizzo().getVia(), 
						u.get().getIndirizzo().getCivico(), 
						u.get().getIndirizzo().getCap(), 
						u.get().getIndirizzo().getCitta()));
	}


	@Override
	public void updateRole(Integer id) throws Exception {

		Optional<Utente> userById = userR.findById(id);

		if(userById.isPresent()) {						
			userById.get().setRuolo(Ruolo.ADMIN);

			userR.save(userById.get());
		}
		log.error("User not found.");
	}
}

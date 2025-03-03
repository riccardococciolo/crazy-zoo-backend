package com.betacom.cz.services.implementations;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.LoginDTO;
import com.betacom.cz.dto.RegisterDTO;
import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.LoginRequest;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.services.interfaces.AuthServices;
import com.betacom.cz.services.interfaces.UtenteServices;
import com.betacom.cz.utils.Jwt;

@Service
public class AuthImplementation implements AuthServices {
	
	@Autowired
	IUtenteRepository utenteR;
	
	@Autowired
	UtenteServices utenteS;
	
	@Autowired
	PasswordEncoder pwdEncoder;
	
	@Autowired
	Jwt jwt;

	@Override
	public RegisterDTO registerUser(UtenteRequest req) {
	    try {
	        //Creazione dell'utente tramite il metodo create()
	        utenteS.create(req);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	    Optional <Utente> utente = utenteR.findByEmail(req.getEmail());
	    
	    return new RegisterDTO(utente.get().getId());

	}

	@Override
	public LoginDTO authenticate(LoginRequest req) throws Exception {
	    
	    Optional<Utente> optionalUtente = utenteR.findByUsername(req.getUsername());

	    if (!optionalUtente.isPresent()) {
	        throw new Exception("Username errato");
	    }

	    Utente utente = optionalUtente.get();

	    if (!pwdEncoder.matches(req.getPassword(), utente.getPassword())) {
	        throw new Exception("Password errata");
	    }

	    String token = jwt.generateToken(utente.getUsername());

	    return new LoginDTO(
	        token,
	        utente.getRuolo().toString(),
	        utente.getId(),
	        utente.getNome(),
	        utente.getCognome(),
	        utente.getUsername(),
	        utente.getEmail(),
	        utente.getCellulare(),
	        utente.getCarrello().getId()
	    );
	}


}

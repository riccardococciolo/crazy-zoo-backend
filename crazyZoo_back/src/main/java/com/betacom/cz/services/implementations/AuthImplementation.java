package com.betacom.cz.services.implementations;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.LoginDTO;
import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.LoginRequest;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseObject;
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
	public void registerUser(UtenteRequest req) {
	    try {
	        //Creazione dell'utente tramite il metodo create()
	        utenteS.create(req);
	    } catch (Exception e) {
	        throw new RuntimeException("Registration failed: " + e.getMessage());
	    }
	}

	@Override
	public ResponseObject<LoginDTO> authenticate(LoginRequest req) {
	    
	    ResponseObject<LoginDTO> response = new ResponseObject<>();
	    
	    //Ricerca utente nel database
	    Optional<Utente> utente = utenteR.findByUsername(req.getUsername());

	    if (utente.isPresent() && pwdEncoder.matches(req.getPassword(), utente.get().getPassword())) {
	        //Genera il token JWT
	        String token = jwt.generateToken(req.getUsername());

	        //Creazione DTO per la risposta
	        LoginDTO loginDTO = new LoginDTO();
	        loginDTO.setToken(token);
	        loginDTO.setUsername(utente.get().getUsername());
	        loginDTO.setRole(utente.get().getRuolo().toString());

	        response.setRc(true);
	        response.setMsg("Login successful");
	        response.setDati(loginDTO);
	    } else {
	        response.setRc(false);
	        response.setMsg("Invalid credentials");
	    }

	    return response;
	}

}

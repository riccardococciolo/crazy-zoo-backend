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
	public RegisterDTO registerUser(UtenteRequest req) {
	    try {
	        //Creazione dell'utente tramite il metodo create()
	        utenteS.create(req);
	    } catch (Exception e) {
	        throw new RuntimeException("Registration failed: " + e.getMessage());
	    }
	    Optional <Utente> utente = utenteR.findByEmail(req.getEmail());
	    
	    return new RegisterDTO(utente.get().getId());

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
	        loginDTO.setRole(utente.get().getRuolo().toString());
	        
	        loginDTO.setId(utente.get().getId());
	        loginDTO.setNome(utente.get().getNome());
	        loginDTO.setCognome(utente.get().getCognome());
	        loginDTO.setUsername(utente.get().getUsername());
	        loginDTO.setEmail(utente.get().getEmail());
	        loginDTO.setCellulare(utente.get().getCellulare());
	        loginDTO.setCarrelloID(utente.get().getCarrello().getId());
	        
	        ;
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

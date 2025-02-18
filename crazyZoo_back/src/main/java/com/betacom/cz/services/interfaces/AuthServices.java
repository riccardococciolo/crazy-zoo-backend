package com.betacom.cz.services.interfaces;

import com.betacom.cz.dto.LoginDTO;
import com.betacom.cz.request.LoginRequest;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseObject;

public interface AuthServices {
	
    void registerUser(UtenteRequest req) throws Exception;
    
    ResponseObject<LoginDTO> authenticate(LoginRequest req);
}

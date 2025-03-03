package com.betacom.cz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.dto.LoginDTO;
import com.betacom.cz.dto.RegisterDTO;
import com.betacom.cz.request.LoginRequest;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.AuthServices;
import com.betacom.cz.services.interfaces.UtenteServices;

@RestController
@RequestMapping("/rest/auth")
@CrossOrigin(origins = "${url_api}")
public class AuthController {

	@Autowired
	AuthServices authS;

	@Autowired
	UtenteServices utenteS;

	@Autowired
	Logger log;

	@PostMapping("/register")
	public ResponseObject<RegisterDTO> register(@RequestBody UtenteRequest request) {

		ResponseObject<RegisterDTO> rObj = new ResponseObject<>();
		rObj.setRc(true);

		try {
			rObj.setDati(authS.registerUser(request));
		} catch (Exception e) {			
			log.error(e.getMessage());

			rObj.setMsg(e.getMessage());
			rObj.setRc(false);
		}

		return rObj;
	}

	@PostMapping("/login")
	public ResponseObject<LoginDTO> login(@RequestBody LoginRequest request) {

		ResponseObject<LoginDTO> rObj = new ResponseObject<>();
		rObj.setRc(true);

		try {
			rObj.setDati(authS.authenticate(request));
		} catch (Exception e) {
			log.error(e.getMessage());

			rObj.setMsg(e.getMessage());
			rObj.setRc(false);
		}

		return rObj;
	}

}

package com.betacom.cz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.request.MailRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.services.interfaces.MailServices;

@RestController
@RequestMapping("/rest/mail")
@CrossOrigin(origins = "${url_api}")
public class MailController {
	
	@Autowired
	MailServices mailS;
	
	@PostMapping("/send")
	public ResponseBase send(@RequestBody MailRequest req) {

		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			mailS.sendMail(req);
		}catch(Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}

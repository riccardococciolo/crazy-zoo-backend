package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.cz.dto.LoginDTO;
import com.betacom.cz.dto.RegisterDTO;
import com.betacom.cz.request.CarrelloRequest;
import com.betacom.cz.request.LoginRequest;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerTest {
	
	@Autowired
	AuthController authC;
	
	@Autowired
	CarrelliController carController;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void register() {
		
		UtenteRequest reqUtente = new UtenteRequest();	
		
		reqUtente.setNome("a");
		reqUtente.setCognome("a");
		reqUtente.setCellulare("3600566451");
		reqUtente.setEmail("msss@gmail.com");
		reqUtente.setPassword("passwordMario");
		reqUtente.setUsername("marioneeeee");
		reqUtente.setRuolo("ADMIN");
		reqUtente.setVia("Via Palla");
		reqUtente.setCivico("29");
		reqUtente.setCap("20133");
		reqUtente.setCitta("Milano");
		
		ResponseObject<RegisterDTO> rO = authC.register(reqUtente);
		
		Assertions.assertThat(rO.getRc()).isEqualTo(true);	
		
		
		CarrelloRequest reqCarrello2 = new CarrelloRequest();
		
		reqCarrello2.setUtenteID(4);
		
		ResponseBase rB2 = carController.create(reqCarrello2);
		
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void login() {
		
		LoginRequest loginReq = new LoginRequest();

		loginReq.setUsername("marioneeeee");
		loginReq.setPassword("passwordMario");
		
		ResponseObject<LoginDTO> rO = authC.login(loginReq);
		
		Assertions.assertThat(rO.getRc()).isEqualTo(true);		
	}

}

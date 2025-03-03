package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteControllerTest {
	
	@Autowired
	UtenteController utController;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void create() {
		
		UtenteRequest reqUtente = new UtenteRequest();	
		
		reqUtente.setNome("Mario");
		reqUtente.setCognome("Rossi");
		reqUtente.setCellulare("3677566451");
		reqUtente.setEmail("mr@gmail.com");
		reqUtente.setPassword("passwordMario");
		reqUtente.setUsername("marione");
		reqUtente.setRuolo("ADMIN");
		reqUtente.setVia("Via Palla");
		reqUtente.setCivico("29");
		reqUtente.setCap("20133");
		reqUtente.setCitta("Milano");
		
		ResponseBase rB = utController.create(reqUtente);
		
		UtenteRequest reqUtente2 = new UtenteRequest();
		
		reqUtente2.setNome("Giorgia");
		reqUtente2.setCognome("Verdi");
		reqUtente2.setCellulare("388866213");
		reqUtente2.setEmail("giorVerd@gmail.com");
		reqUtente2.setPassword("passwordGiorgia");
		reqUtente2.setUsername("georgina");
		reqUtente2.setRuolo("CLIENTE");
		reqUtente2.setVia("Via Stella");
		reqUtente2.setCivico("24");
		reqUtente2.setCap("22133");
		reqUtente2.setCitta("Roma");
		
		ResponseBase rB2 = utController.create(reqUtente2);
		
		UtenteRequest reqUtente3 = new UtenteRequest();
		
		reqUtente3.setNome("Svetlana");
		reqUtente3.setCognome("Bianchi");
		reqUtente3.setCellulare("388321456");
		reqUtente3.setEmail("svetlanaB@gmail.com");
		reqUtente3.setPassword("passwordSvetlana");
		reqUtente3.setUsername("Svetti");
		reqUtente3.setRuolo("CLIENTE");
		reqUtente3.setVia("Via Cubo");
		reqUtente3.setCivico("27");
		reqUtente3.setCap("21133");
		reqUtente3.setCitta("Torino");
		
		ResponseBase rB3 = utController.create(reqUtente3);
		
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);
		Assertions.assertThat(rB3.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void listAll() {
	    
	    ResponseList<UtenteDTO> resList = utController.listAll(); 

	    Assertions.assertThat(resList.getRc()).isEqualTo(true);	

	    Assertions.assertThat(resList.getDati().get(0).getUsername()).isEqualTo("marione");		
	}
	
	@Test
	@Order(3)
	public void listById() {
		ResponseObject<UtenteDTO> rB = utController.listByID(1);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(4)
	public void delete() {

	    ResponseList<UtenteDTO> rL = utController.listAll();

	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    UtenteDTO utenteDaEliminare = rL.getDati().stream()
	        .filter(m -> "georgina".equals(m.getUsername())) 
	        .findFirst()
	        .orElse(null);

	    Assertions.assertThat(utenteDaEliminare).isNotNull();

	    UtenteRequest utenteReq = new UtenteRequest();
	    utenteReq.setId(utenteDaEliminare.getId()); 

	    ResponseBase uRB = utController.delete(utenteReq);
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    ResponseList<UtenteDTO> uRL = utController.listAll();

	    Assertions.assertThat(uRL.getRc()).isTrue();

	    UtenteDTO utenteEliminato = uRL.getDati().stream()
	        .filter(m -> "georgina".equals(m.getUsername())) 
	        .findFirst()
	        .orElse(null);

	    Assertions.assertThat(utenteEliminato).isNull();
	}

}

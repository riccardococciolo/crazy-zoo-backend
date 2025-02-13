package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.dto.CarrelloDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.CarrelloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelliControllerTest {

	@Autowired
	CarrelliController carController;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void create() {
		
		CarrelloRequest reqCarrello = new CarrelloRequest();	
		
		reqCarrello.setUtenteID(1);
		
		ResponseBase rB = carController.create(reqCarrello);
		
		CarrelloRequest reqCarrello2 = new CarrelloRequest();
		
		reqCarrello2.setUtenteID(3);
		
		ResponseBase rB2 = carController.create(reqCarrello2);
		
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void listAll() {
	    
	    ResponseList<CarrelloDTO> resList = carController.listAll(); 

	    Assertions.assertThat(resList.getRc()).isEqualTo(true);	

	    Assertions.assertThat(resList.getDati().get(0).getId()).isEqualTo(1);		
	}
	@Test
	@Order(3)
	public void listbyid() {
	    
		ResponseList<ProdottoDTO> resList = carController.listByID(1); 

	    Assertions.assertThat(resList.getRc()).isEqualTo(true);	

	   // Assertions.assertThat(resList.getDati().get(0).getId()).isEqualTo(2);		
	}
	
	@Test
	@Order(4)
	public void delete() 
	{
	    ResponseList<CarrelloDTO> rL = carController.listAll();

	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    CarrelloDTO carrelloDaEliminare = rL.getDati().stream()
	        .filter(m -> 2 == m.getId()) 
	        .findFirst()
	        .orElse(null);

	    Assertions.assertThat(carrelloDaEliminare).isNotNull();

	    CarrelloRequest carrelloReq = new CarrelloRequest();
	    carrelloReq.setId(carrelloDaEliminare.getId()); 

	    ResponseBase uRB = carController.delete(carrelloReq);
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    ResponseList<CarrelloDTO> uRL = carController.listAll();

	    Assertions.assertThat(uRL.getRc()).isTrue();

	    CarrelloDTO carrelloEliminato = uRL.getDati().stream()
	        .filter(m -> 2 == m.getId()) 
	        .findFirst()
	        .orElse(null);

	    Assertions.assertThat(carrelloEliminato).isNull();
	}
}

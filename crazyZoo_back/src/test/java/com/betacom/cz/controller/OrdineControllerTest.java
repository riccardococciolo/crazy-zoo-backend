package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.cz.dto.OrdineDTO;
import com.betacom.cz.request.OrdineRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineControllerTest {

	@Autowired
	OrdineController ordineController;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void create() {
		
		OrdineRequest reqOrdine = new OrdineRequest();		
		reqOrdine.setUtenteID(1);	
		ResponseBase rB = ordineController.create(reqOrdine);			
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void listById() {
	    
	    ResponseObject<OrdineDTO> resObj = ordineController.listByID(1); 

	    Assertions.assertThat(resObj.getRc()).isEqualTo(true);	

	    Assertions.assertThat(resObj.getDati().getId()).isEqualTo(1);		
	}
	
	@Test
	@Order(3)
	public void delete() 
	{
	    ResponseObject<OrdineDTO> rObj = ordineController.listByID(1);
	    System.out.println("PRIMA della cancellazione: " + rObj.getDati());

	    Assertions.assertThat(rObj.getRc()).isTrue();
	    Assertions.assertThat(rObj.getDati()).isNotNull();

	    OrdineDTO ordineDaEliminare = rObj.getDati();
	    Assertions.assertThat(ordineDaEliminare).isNotNull();

	    OrdineRequest ordineReq = new OrdineRequest();
	    ordineReq.setId(ordineDaEliminare.getId());

	    ResponseBase uRB = ordineController.delete(ordineReq);
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    ResponseObject<OrdineDTO> uRL = ordineController.listByID(1);
	    System.out.println("DOPO la cancellazione: " + uRL.getDati());

	    Assertions.assertThat(uRL.getRc()).isFalse();
	    Assertions.assertThat(uRL.getDati()).isNull();
	}

	@Test
	@Order(4)
	public void createBis() {
		
		OrdineRequest reqOrdine = new OrdineRequest();		
		reqOrdine.setUtenteID(3);	
		ResponseBase rB = ordineController.create(reqOrdine);			
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(5)
	public void listByUtente() {
		
		ResponseList<OrdineDTO> lO = ordineController.listByUtente(3);
		
		Assertions.assertThat(lO.getRc()).isEqualTo(true);
		Assertions.assertThat(lO.getDati()).isNotEmpty();
	}
	
	@Test
	@Order(6)
	public void listAll() {
		
		ResponseList<OrdineDTO> lO = ordineController.listAll();
		
		Assertions.assertThat(lO.getRc()).isEqualTo(true);
		Assertions.assertThat(lO.getDati()).isNotEmpty();
	}
	

}

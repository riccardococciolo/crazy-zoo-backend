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
		reqOrdine.setCarrelloID(null);
		
		ResponseBase rB = ordineController.create(reqOrdine);
		
		OrdineRequest reqOrdine2 = new OrdineRequest();
		
		reqOrdine2.setUtenteID(1);
		
		ResponseBase rB2 = ordineController.create(reqOrdine2);
		
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
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

	    Assertions.assertThat(rObj.getRc()).isTrue();
	    Assertions.assertThat(rObj.getDati()).isNotNull();

	    OrdineDTO ordineDaEliminare = rObj.getDati();

	    Assertions.assertThat(ordineDaEliminare).isNotNull();

	    OrdineRequest ordineReq = new OrdineRequest();
	    ordineReq.setId(ordineDaEliminare.getId()); 

	    ResponseBase uRB = ordineController.delete(ordineReq);
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    ResponseObject<OrdineDTO> uRL = ordineController.listByID(1);

	    Assertions.assertThat(uRL.getRc()).isFalse();

	    OrdineDTO ordineEliminato = uRL.getDati();
	    
	    Assertions.assertThat(ordineEliminato).isNull();
	}
}

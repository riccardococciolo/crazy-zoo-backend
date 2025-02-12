package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.cz.request.ProdottoOrdineRequest;
import com.betacom.cz.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoOrdineControllerTest {

	@Autowired
	ProdottoOrdineController poController;
	
	@Test
	@Order(1)
	public void addProdotto() {
	    	
		ProdottoOrdineRequest prodOrdReq = new ProdottoOrdineRequest();
		
		prodOrdReq.setOrdineID(2);
		prodOrdReq.setProdottoID(1);
		
		ResponseBase rB = poController.aggiungiProdottoAOrdine(prodOrdReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void deleteById() {
		ProdottoOrdineRequest prodOrdReq = new ProdottoOrdineRequest();
		
		prodOrdReq.setOrdineID(2);
		prodOrdReq.setProdottoID(1);
		
		ResponseBase rB = poController.removeById(prodOrdReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	

	}
	
	@Test
	@Order(3)
	public void deleteAll() {
		ProdottoOrdineRequest prodOrdReq = new ProdottoOrdineRequest();
		
		prodOrdReq.setOrdineID(2);
		prodOrdReq.setProdottoID(1);
		
		ResponseBase rB = poController.removeAll(prodOrdReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	

	}
}

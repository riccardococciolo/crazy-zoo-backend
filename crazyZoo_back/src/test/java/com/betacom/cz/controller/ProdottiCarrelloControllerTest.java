package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.repositories.IImmagineRepository;
import com.betacom.cz.request.ProdottiCarrelloRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottiCarrelloControllerTest {
	
	@Autowired
	ProdottiCarrelloController pcController;
	
	@Autowired
	IImmagineRepository imgR;
	
	@Test
	@Order(1)
	public void addCarrello() {
	    	
		ProdottiCarrelloRequest prodCarReq = new ProdottiCarrelloRequest(1, 1);
		
		ResponseBase rB = pcController.addProdottoToCarrello(prodCarReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		
		ProdottiCarrelloRequest prodCarReq2 = new ProdottiCarrelloRequest(3, 1);
		
		ResponseBase rB2 = pcController.addProdottoToCarrello(prodCarReq2);
		
		Assertions.assertThat(rB2).isNotNull();
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void listByIdCarrello() {
		ProdottiCarrelloRequest prodCarReq = new ProdottiCarrelloRequest(1,1);
		ResponseList<ProdottoDTO> rB = pcController.listByIdCarrello(prodCarReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
	}
	@Test
	@Order(3)
	public void deleteById() {
		ProdottiCarrelloRequest prodCarReq = new ProdottiCarrelloRequest(1,1);
		ResponseBase rB = pcController.deletepbyid(prodCarReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	

	}
	
	@Test
	@Order(4)
	public void deleteAllProd() {
		ProdottiCarrelloRequest prodCarReq = new ProdottiCarrelloRequest(1,1);
		ResponseBase rB = pcController.deleteprodottoallinc(prodCarReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	

	}
	
	@Test
	@Order(5)
	public void addCarrelloBis() {
	    	
		ProdottiCarrelloRequest prodCarReq = new ProdottiCarrelloRequest(1, 1);
		
		ResponseBase rB = pcController.addProdottoToCarrello(prodCarReq);
		
		Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		
		
		ProdottiCarrelloRequest prodCarReq2 = new ProdottiCarrelloRequest(1, 3);
		
		ResponseBase rB2 = pcController.addProdottoToCarrello(prodCarReq2);
		
		Assertions.assertThat(rB2).isNotNull();
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
		
		
	}
}

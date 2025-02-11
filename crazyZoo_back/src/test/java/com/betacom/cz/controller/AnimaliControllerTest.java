package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.request.AnimaleRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimaliControllerTest {
	
	@Autowired
	AnimaleController animController;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void create() {
		
		AnimaleRequest reqAnimale = new AnimaleRequest();	
		
		reqAnimale.setNomeAnimale("cane");
		
		ResponseBase rB = animController.create(reqAnimale);
		
		AnimaleRequest reqAnimale2 = new AnimaleRequest();
		
		reqAnimale2.setNomeAnimale("lemure");
		
		ResponseBase rB2 = animController.create(reqAnimale2);
		
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void listAll() {
		
		ResponseList<AnimaleDTO> resList = animController.listAll(); 
		
		Assertions.assertThat(resList.getRc()).isEqualTo(true);		
		Assertions.assertThat(resList.getDati().get(0).getNome()).isEqualTo("cane");		
	}
	
	@Test
	@Order(3)
	public void update() {

		ResponseList<AnimaleDTO> responseList = animController.listAll();

	    Assertions.assertThat(responseList.getRc()).isTrue();
	    Assertions.assertThat(responseList.getDati()).isNotEmpty();

	    AnimaleDTO animaleDaAggiornare = responseList.getDati().stream()
	        .filter(a -> "cane".equals(a.getNome()))
	        .findFirst()
	        .orElse(null);
	    Assertions.assertThat(animaleDaAggiornare).isNotNull();

	    AnimaleRequest reqAnimale = new AnimaleRequest();	
	    reqAnimale.setNomeAnimale("gatto");
	    
	    ResponseBase updateResponse = animController.update(reqAnimale);

	    Assertions.assertThat(updateResponse.getRc()).isTrue();

	    ResponseList<AnimaleDTO> updatedResponseList = animController.listAll();
	    Assertions.assertThat(updatedResponseList.getRc()).isTrue();

	    AnimaleDTO animaleAggiornato = updatedResponseList.getDati().stream()
	        .filter(a -> "cane".equals(a.getNome()))
	        .findFirst()
	        .orElse(null);
	    Assertions.assertThat(animaleAggiornato).isNotNull();

	    Assertions.assertThat(animaleAggiornato.getNome()).isEqualTo("topo");
	}
	
	@Test
	@Order(4)
	public void delete() {
		
		AnimaleRequest reqAnimale = new AnimaleRequest();	
		 
	    ResponseList<AnimaleDTO> responseList = animController.listAll();
	    Assertions.assertThat(responseList.getRc()).isTrue();
	    Assertions.assertThat(responseList.getDati()).isNotEmpty();

	    AnimaleDTO animaleDto = responseList.getDati().stream()
	        .filter(a -> "cane".equals(a.getNome()))
	        .findFirst()
	        .orElse(null);
	    Assertions.assertThat(animaleDto).isNotNull();

	    ResponseBase resBase = animController.delete(reqAnimale);
	    Assertions.assertThat(resBase.getRc()).isTrue();

	    ResponseList<AnimaleDTO> postDeleteListResponse = animController.listAll();
	    Assertions.assertThat(postDeleteListResponse.getRc()).isTrue();

	    AnimaleDTO animaleDopoEliminazione = postDeleteListResponse.getDati().stream()
	        .filter(a -> "cane".equals(a.getNome()))
	        .findFirst()
	        .orElse(null);
	    Assertions.assertThat(animaleDopoEliminazione).isNull();	
	}
}

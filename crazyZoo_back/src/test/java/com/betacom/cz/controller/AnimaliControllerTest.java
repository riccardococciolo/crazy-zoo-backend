package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.cz.request.AnimaleRequest;
import com.betacom.cz.response.ResponseBase;

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
	
//	@Test
//	@Order(2)
//	public void listAll() {
//		
//		ResponseList<MacchinaDTO> rL = macC.listAll(); 
//		
//		Assertions.assertThat(rL.getRc()).isEqualTo(true);		
//		Assertions.assertThat(rL.getDati().get(0).getTargaMacchina()).isEqualTo("EE777ZZ");		
//	}
//	
//	@Test
//	@Order(3)
//	public void update() {
//
//		ResponseList<MacchinaDTO> responseList = macC.listAll();
//
//	    Assertions.assertThat(responseList.getRc()).isTrue();
//	    Assertions.assertThat(responseList.getDati()).isNotEmpty();
//
//	    MacchinaDTO macchinaDaAggiornare = responseList.getDati().stream()
//	        .filter(m -> "EE777ZZ".equals(m.getTargaMacchina()))
//	        .findFirst()
//	        .orElse(null);
//	    Assertions.assertThat(macchinaDaAggiornare).isNotNull();
//
//	    VeicoloMacchinaReq reqVM = new VeicoloMacchinaReq();
//	    reqVM.getReqV().setIdAlimentazione(1); 
//	    reqVM.getReqV().setIdColore(2); 
//	    reqVM.getReqV().setNumeroRuote(4);
//	    reqVM.getReqV().setDataInserimento("22/01/2025"); 
//	    reqVM.getReqV().setMarca("Fiat");
//	    reqVM.getReqV().setTipoVeicolo("Macchina");
//	    reqVM.getReqM().setNumeroPorte(4); 
//	    reqVM.getReqM().setTargaMacchina("EE777ZZ"); 
//	    reqVM.getReqM().setCcMacchina(180); 
//	    
//	    ResponseBase updateResponse = macC.update(reqVM, macchinaDaAggiornare.getTargaMacchina());
//
//	    Assertions.assertThat(updateResponse.getRc()).isTrue();
//
//	    ResponseList<MacchinaDTO> updatedResponseList = macC.listAll();
//	    Assertions.assertThat(updatedResponseList.getRc()).isTrue();
//
//	    MacchinaDTO macchinaAggiornata = updatedResponseList.getDati().stream()
//	        .filter(m -> "EE777ZZ".equals(m.getTargaMacchina()))
//	        .findFirst()
//	        .orElse(null);
//	    Assertions.assertThat(macchinaAggiornata).isNotNull();
//
//	    Assertions.assertThat(macchinaAggiornata.getVeicolo().getColore().getNomeColore()).isEqualTo("Rosso");
//	    Assertions.assertThat(macchinaAggiornata.getNumeroPorte()).isEqualTo(4);
//	    Assertions.assertThat(macchinaAggiornata.getCcMacchina()).isEqualTo(180);
//	}
//	
//	@Test
//	@Order(4)
//	public void delete() {
//		
//	    ResponseList<MacchinaDTO> rL = macC.listAll();
//	    Assertions.assertThat(rL.getRc()).isTrue();
//	    Assertions.assertThat(rL.getDati()).isNotEmpty();
//
//	    MacchinaDTO mC = rL.getDati().stream()
//	        .filter(m -> "FF777ZZ".equals(m.getTargaMacchina()))
//	        .findFirst()
//	        .orElse(null);
//	    Assertions.assertThat(mC).isNotNull();
//
//	    ResponseBase rB = macC.delete(mC.getVeicolo().getMacchina().getTargaMacchina());
//	    Assertions.assertThat(rB.getRc()).isTrue();
//
//	    ResponseList<MacchinaDTO> postDeleteListResponse = macC.listAll();
//	    Assertions.assertThat(postDeleteListResponse.getRc()).isTrue();
//
//	    MacchinaDTO macchinaDopoEliminazione = postDeleteListResponse.getDati().stream()
//	        .filter(m -> "FF777ZZ".equals(m.getTargaMacchina()))
//	        .findFirst()
//	        .orElse(null);
//	    Assertions.assertThat(macchinaDopoEliminazione).isNull();	
//	}
}

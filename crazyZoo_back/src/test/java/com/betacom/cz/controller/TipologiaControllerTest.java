package com.betacom.cz.controller;

import java.util.Comparator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.dto.TipologiaDTO;
import com.betacom.cz.request.TipologiaRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipologiaControllerTest {
	
	@Autowired
	TipologiaController tipologiaC;
	
	@Test
	@Order(1)
	public void create() {
		
		TipologiaRequest reqT = new TipologiaRequest();		
		reqT.setNome("Cibo secco");	
		ResponseBase rB = tipologiaC.create(reqT);
		
		TipologiaRequest reqT2 = new TipologiaRequest();
		reqT2.setNome("Cibo umido");
		ResponseBase rB2 = tipologiaC.create(reqT2);
			
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void listAll() {
	    
	    ResponseList<TipologiaDTO> rL = tipologiaC.listAll();    
	    Assertions.assertThat(rL.getRc()).isEqualTo(true);    

	    List<TipologiaDTO> rLsort = rL.getDati().stream()
	                              .sorted(Comparator.comparing(TipologiaDTO::getId)) 
	                              .toList(); 

	    Assertions.assertThat(rLsort.get(0).getNome()).isEqualTo("Cibo secco");  
	}
	
	@Test
	@Order(3)
	public void listById() {
		ResponseObject<TipologiaDTO> rO = tipologiaC.listByID(1);
		
		Assertions.assertThat(rO).isNotNull();
		Assertions.assertThat(rO.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(4)
	public void update() {
		
	    ResponseList<TipologiaDTO> rL = tipologiaC.listAll();
	    
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    TipologiaDTO tipologiaDaAggiornare = rL.getDati().stream()
	        .filter(m -> "Cibo umido".equals(m.getNome()))
	        .findFirst()
	        .orElse(null);
	    
	    Assertions.assertThat(tipologiaDaAggiornare).isNotNull();
	    
	    TipologiaRequest reqT = new TipologiaRequest();
	    reqT.setId(tipologiaDaAggiornare.getId()); 
	    reqT.setNome("ProvaUpdate"); 
	    
	    ResponseBase uRB = tipologiaC.update(reqT);
	    
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    ResponseList<TipologiaDTO> uRL = tipologiaC.listAll();
	    
	    Assertions.assertThat(uRL.getRc()).isTrue();

	    TipologiaDTO tipologiaAggiornata = uRL.getDati().stream()
	        .filter(m -> "ProvaUpdate".equals(m.getNome()))
	        .findFirst()
	        .orElse(null);
	    
	    Assertions.assertThat(tipologiaAggiornata).isNotNull();
	    Assertions.assertThat(tipologiaAggiornata.getNome()).isEqualTo("ProvaUpdate");
	}
	
	@Test
	@Order(5)
	public void delete() {
	    
	    ResponseList<TipologiaDTO> rL = tipologiaC.listAll();
	    
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    TipologiaDTO tipologiaDaEliminare = rL.getDati().stream()
	        .filter(t -> "Cibo secco".equals(t.getNome())) 
	        .findFirst()
	        .orElse(null);
	    
	    Assertions.assertThat(tipologiaDaEliminare).isNotNull();
	    
	    TipologiaRequest reqT = new TipologiaRequest();
	    reqT.setId(tipologiaDaEliminare.getId()); 
	    
	    ResponseBase uRB = tipologiaC.delete(reqT);
	    
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    ResponseList<TipologiaDTO> uRL = tipologiaC.listAll();
	    
	    Assertions.assertThat(uRL.getRc()).isTrue();

	    TipologiaDTO tipologiaEliminata = uRL.getDati().stream()
	        .filter(t -> "Cibo secco".equals(t.getNome())) 
	        .findFirst()
	        .orElse(null);
	    
	    Assertions.assertThat(tipologiaEliminata).isNull();
	}

}

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
import com.betacom.cz.response.ResponseObject;

@SpringBootTest(properties = "spring.profiles.active=test")
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
	public void listById() {
		ResponseObject<AnimaleDTO> rO = animController.listByID(1);
		
		Assertions.assertThat(rO).isNotNull();
		Assertions.assertThat(rO.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(4)
    public void update() {

        ResponseList<AnimaleDTO> rL = animController.listAll();

        Assertions.assertThat(rL.getRc()).isTrue();
        Assertions.assertThat(rL.getDati()).isNotEmpty();

        AnimaleDTO animaleDaAggiornare = rL.getDati().stream()
            .filter(m -> "cane".equals(m.getNome()))
            .findFirst()
            .orElse(null);

        Assertions.assertThat(animaleDaAggiornare).isNotNull();

        AnimaleRequest reqAnimale = new AnimaleRequest();
        reqAnimale.setId(animaleDaAggiornare.getId()); 
        reqAnimale.setNomeAnimale("cavallo"); 

        ResponseBase uRB = animController.update(reqAnimale);

        Assertions.assertThat(uRB.getRc()).isTrue();

        ResponseList<AnimaleDTO> uRL = animController.listAll();

        Assertions.assertThat(uRL.getRc()).isTrue();

        AnimaleDTO animaleAggiornato = uRL.getDati().stream()
            .filter(m -> "cavallo".equals(m.getNome()))
            .findFirst()
            .orElse(null);

        Assertions.assertThat(animaleAggiornato).isNotNull();
        Assertions.assertThat(animaleAggiornato.getNome()).isEqualTo("cavallo");
    }
	
	@Test
	@Order(5)
    public void delete() {

        ResponseList<AnimaleDTO> rL = animController.listAll();

        Assertions.assertThat(rL.getRc()).isTrue();
        Assertions.assertThat(rL.getDati()).isNotEmpty();

        AnimaleDTO animaleDaEliminare = rL.getDati().stream()
            .filter(m -> "lemure".equals(m.getNome())) 
            .findFirst()
            .orElse(null);

        Assertions.assertThat(animaleDaEliminare).isNotNull();

        AnimaleRequest animaleReq = new AnimaleRequest();
        animaleReq.setId(animaleDaEliminare.getId()); 

        ResponseBase uRB = animController.delete(animaleReq);

        Assertions.assertThat(uRB.getRc()).isTrue();

        ResponseList<AnimaleDTO> uRL = animController.listAll();

        Assertions.assertThat(uRL.getRc()).isTrue();

        AnimaleDTO animaleEliminato = uRL.getDati().stream()
            .filter(m -> "lemure".equals(m.getNome())) 
            .findFirst()
            .orElse(null);

        Assertions.assertThat(animaleEliminato).isNull();
    }
}

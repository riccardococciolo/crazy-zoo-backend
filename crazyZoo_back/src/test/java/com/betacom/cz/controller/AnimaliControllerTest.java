package com.betacom.cz.controller;

import java.util.Comparator;
import java.util.List;

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
//		 List<AnimaleDTO> rLsort = resList.getDati().stream()
//                 .sorted(Comparator.comparing(AnimaleDTO::getId))
//                 .toList();
		Assertions.assertThat(resList.getDati().get(0).getNome()).isEqualTo("cane");		
	}
	
	@Test
	@Order(3)
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
	@Order(4)
    public void delete() {

        //Recupera tutte le marche
        ResponseList<AnimaleDTO> rL = animController.listAll();

        //Verifica che la lista sia popolata
        Assertions.assertThat(rL.getRc()).isTrue();
        Assertions.assertThat(rL.getDati()).isNotEmpty();

        //Trova la marca da eliminare
        AnimaleDTO animaleDaEliminare = rL.getDati().stream()
            .filter(m -> "lemure".equals(m.getNome())) 
            .findFirst()
            .orElse(null);

        //Verifica che esista la marca
        Assertions.assertThat(animaleDaEliminare).isNotNull();

        //Crea la richiesta di eliminazione
        AnimaleRequest animaleReq = new AnimaleRequest();
        animaleReq.setId(animaleDaEliminare.getId()); 

        //Esegui il delete
        ResponseBase uRB = animController.delete(animaleReq);

        //Verifica che il delete sia andato a buon fine
        Assertions.assertThat(uRB.getRc()).isTrue();

        //Recupera nuovamente tutte le marche per verificare che sia stata eliminata
        ResponseList<AnimaleDTO> uRL = animController.listAll();

        Assertions.assertThat(uRL.getRc()).isTrue();

        //Cerca la marca eliminata
        AnimaleDTO animaleEliminato = uRL.getDati().stream()
            .filter(m -> "lemure".equals(m.getNome())) 
            .findFirst()
            .orElse(null);

        //Verifica che la marca eliminata NON esista più
        Assertions.assertThat(animaleEliminato).isNull();
    }
}

package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.dto.RecensioneDTO;
import com.betacom.cz.request.RecensioniRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecensioneControllerTest {
	
	@Autowired
	RecensioneController recensioneC;
	
	@Test
    @Order(1)
    public void create() {
        RecensioniRequest reqR1 = new RecensioniRequest();
        reqR1.setProdottoID(1); 
        reqR1.setUtenteID(1);   
        reqR1.setDescrizione("Ottimo prodotto!");
        reqR1.setValutazione(5);

        ResponseBase rE1 = recensioneC.create(reqR1);

        Assertions.assertThat(rE1).isNotNull();
        Assertions.assertThat(rE1.getRc()).isTrue();
    }

    @Test
    @Order(2)
    public void listByProdotto() {
        ResponseList<RecensioneDTO> response = recensioneC.listByProdotto(1);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(response.getDati()).isNotEmpty(); 
    }

    @Test
    @Order(3)
    public void listByUtente() {
        ResponseList<RecensioneDTO> response = recensioneC.listByUtente(1);

        System.out.println("Response Rc: " + response.getRc());
        System.out.println("Numero recensioni trovate: " + (response.getDati() != null ? response.getDati().size() : "null"));

        
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(response.getDati()).isNotEmpty();
    }

    @Test
    @Order(4)
    public void delete() {
        ResponseList<RecensioneDTO> rL = recensioneC.listByProdotto(1);

        Assertions.assertThat(rL.getRc()).isTrue();
        Assertions.assertThat(rL.getDati()).isNotEmpty();

        RecensioneDTO recensioneDaEliminare = rL.getDati().stream()
            .findFirst()
            .orElse(null);

        Assertions.assertThat(recensioneDaEliminare).isNotNull();

        RecensioniRequest reqR = new RecensioniRequest();
        reqR.setId(recensioneDaEliminare.getId());

        ResponseBase uRB = recensioneC.delete(reqR);
        Assertions.assertThat(uRB.getRc()).isTrue();

        ResponseList<RecensioneDTO> uRL = recensioneC.listByProdotto(1);

        Assertions.assertThat(uRL.getRc()).isTrue();

        RecensioneDTO recensioneEliminata = uRL.getDati().stream()
            .filter(r -> r.getId().equals(recensioneDaEliminare.getId()))
            .findFirst()
            .orElse(null);

        Assertions.assertThat(recensioneEliminata).isNull();
    }



    
    


}

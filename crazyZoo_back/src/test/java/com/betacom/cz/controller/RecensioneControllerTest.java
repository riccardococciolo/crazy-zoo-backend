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
        //Creazione della richiesta di recensione
        RecensioniRequest reqR1 = new RecensioniRequest();
        reqR1.setProdottoID(1); 
        reqR1.setUtenteID(1);   
        reqR1.setDescrizione("Ottimo prodotto!");
        reqR1.setValutazione(5);

        //Chiamata al controller per la creazione della recensione
        ResponseBase rE1 = recensioneC.create(reqR1);

        // Verifica della risposta
        Assertions.assertThat(rE1).isNotNull();
        Assertions.assertThat(rE1.getRc()).isTrue();
    }

    @Test
    @Order(2)
    public void listByProdotto() {
        //Recupero recensioni per il prodotto con ID 1
        ResponseList<RecensioneDTO> response = recensioneC.listByProdotto(1);

        //Verifica che il recupero sia andato a buon fine
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getRc()).isTrue();
        Assertions.assertThat(response.getDati()).isNotEmpty(); 
    }

    @Test
    @Order(3)
    public void listByUtente() {
        // Recupera le recensioni per l'utente con ID 1
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
        // Recupera la lista di recensioni per il prodotto con ID 1
        ResponseList<RecensioneDTO> rL = recensioneC.listByProdotto(1);

        // Verifica che la lista non sia vuota e che la risposta sia valida
        Assertions.assertThat(rL.getRc()).isTrue();
        Assertions.assertThat(rL.getDati()).isNotEmpty();

        // Trova la recensione da eliminare (la prima disponibile)
        RecensioneDTO recensioneDaEliminare = rL.getDati().stream()
            .findFirst()
            .orElse(null);

        // Assicuriamoci che esista
        Assertions.assertThat(recensioneDaEliminare).isNotNull();

        // Crea una richiesta di eliminazione con l'ID della recensione
        RecensioniRequest reqR = new RecensioniRequest();
        reqR.setId(recensioneDaEliminare.getId());

        // Elimina la recensione
        ResponseBase uRB = recensioneC.delete(reqR);
        Assertions.assertThat(uRB.getRc()).isTrue();

        // Recupera nuovamente la lista delle recensioni per verificare che sia stata eliminata
        ResponseList<RecensioneDTO> uRL = recensioneC.listByProdotto(1);

        Assertions.assertThat(uRL.getRc()).isTrue();

        // Verifica che la recensione eliminata non sia più nella lista
        RecensioneDTO recensioneEliminata = uRL.getDati().stream()
            .filter(r -> r.getId().equals(recensioneDaEliminare.getId()))
            .findFirst()
            .orElse(null);

        // Deve essere null, indicando che è stata eliminata
        Assertions.assertThat(recensioneEliminata).isNull();
    }



    
    


}

package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.request.ProdottoRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoControllerTest {
	
	@Autowired
	ProdottoController prodC;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void create() {
	    
	    //Creazione del primo prodotto
	    ProdottoRequest reqP1 = new ProdottoRequest();        
	    reqP1.setPrezzo(9.99);
	    reqP1.setQuantita(3);
	    reqP1.setTitolo("ProdottoBalordo");
	    reqP1.setAnimaleID(1);
	    reqP1.setMarcaID(2);
	    reqP1.setTipologiaID(2);
	    reqP1.setDescrizione("Prodotto bello");

	    //Simula un'immagine finta
	    MockMultipartFile mockImage1 = new MockMultipartFile(
	        "file", "test1.jpg", "image/jpeg", "fake image content".getBytes()
	    );

	    MultipartFile[] immaginiArray1 = { mockImage1 };
	    reqP1.setImmagini(immaginiArray1); 
	    
	    //Chiamata al controller per il primo prodotto
	    ResponseEntity<ResponseBase> rE1 = prodC.create(reqP1);
	    ResponseBase rB1 = rE1.getBody();

	    Assertions.assertThat(rB1).isNotNull();
	    Assertions.assertThat(rB1.getRc()).isTrue();

	    //Creazione del secondo prodotto (per il delete)
	    ProdottoRequest reqP2 = new ProdottoRequest();        
	    reqP2.setPrezzo(14.99);
	    reqP2.setQuantita(5);
	    reqP2.setTitolo("ProdottoDaEliminare");
	    reqP2.setAnimaleID(1);
	    reqP2.setMarcaID(2);
	    reqP2.setTipologiaID(2);
	    reqP2.setDescrizione("Prodotto da eliminare");

	    //Simula un'altra immagine
	    MockMultipartFile mockImage2 = new MockMultipartFile(
	        "file", "test2.jpg", "image/jpeg", "another fake image content".getBytes()
	    );

	    MultipartFile[] immaginiArray2 = { mockImage2 };
	    reqP2.setImmagini(immaginiArray2); 
	    
	    //Chiamata al controller per il secondo prodotto
	    ResponseEntity<ResponseBase> rE2 = prodC.create(reqP2);
	    ResponseBase rB2 = rE2.getBody();

	    Assertions.assertThat(rB2).isNotNull();
	    Assertions.assertThat(rB2.getRc()).isTrue();
	}
	
	@Test
	@Order(2)
	public void listByFilter() {
	    
	    //Recupera il prodotto usando il filtro per titolo
	    ResponseList<ProdottoDTO> rL = prodC.list(null, "ProdottoBalordo", null, null, null, null, null, null);

	    //Verifica che la chiamata sia andata a buon fine
	    Assertions.assertThat(rL.getRc()).isTrue();
	    
	    //Verifica che il prodotto sia stato trovato
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova il primo prodotto che corrisponde al filtro
	    ProdottoDTO prodottoTrovato = rL.getDati().stream().findFirst().orElse(null);
	    
	    //Verifica che il prodotto trovato non sia null
	    Assertions.assertThat(prodottoTrovato).isNotNull();

	    //Controlla che i dati corrispondano a quelli attesi
	    Assertions.assertThat(prodottoTrovato.getTitolo()).isEqualTo("ProdottoBalordo");
	    Assertions.assertThat(prodottoTrovato.getPrezzo()).isEqualTo(9.99);
	    Assertions.assertThat(prodottoTrovato.getQuantita()).isEqualTo(3);
	}
	
	@Test
	@Order(3)
	public void update() {

	    //Recupera il prodotto da aggiornare tramite listByFilter
	    ResponseList<ProdottoDTO> rL = prodC.list(null, "ProdottoBalordo", null, null, null, null, null, null);
	    
	    //Verifica che la lista non sia vuota e che il prodotto esista
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova il primo prodotto che corrisponde al filtro
	    ProdottoDTO prodottoDaAggiornare = rL.getDati().stream().findFirst().orElse(null);
	    
	    //Verifica che il prodotto esista
	    Assertions.assertThat(prodottoDaAggiornare).isNotNull();
	    
	    //Crea una richiesta di update con i nuovi valori
	    ProdottoRequest reqP = new ProdottoRequest();
	    reqP.setId(prodottoDaAggiornare.getId()); 
	    reqP.setPrezzo(15.99); 
	    reqP.setQuantita(10);    
	    reqP.setTitolo("ProdottoAggiornato"); 
	    reqP.setAnimaleID(prodottoDaAggiornare.getAnimale().getId());
	    reqP.setMarcaID(prodottoDaAggiornare.getMarca().getId());
	    reqP.setTipologiaID(prodottoDaAggiornare.getTipologia().getId());
	    reqP.setDescrizione("Nuova descrizione aggiornata");

	    //Esegui l'update
	    ResponseEntity<ResponseBase> rE = prodC.update(reqP);

	    //Verifica che l'update sia andato a buon fine
	    ResponseBase rB = rE.getBody();
	    Assertions.assertThat(rB).isNotNull();
	    Assertions.assertThat(rB.getRc()).isTrue();

	    //Recupera di nuovo il prodotto aggiornato
	    ResponseList<ProdottoDTO> rLUpdated = prodC.list(null, "ProdottoAggiornato", null, null, null, null, null, null);
	    
	    //Verifica che la lista non sia vuota e che il prodotto aggiornato esista
	    Assertions.assertThat(rLUpdated.getRc()).isTrue();
	    Assertions.assertThat(rLUpdated.getDati()).isNotEmpty();

	    //Trova il prodotto aggiornato
	    ProdottoDTO prodottoAggiornato = rLUpdated.getDati().stream().findFirst().orElse(null);
	    
	    //Verifica che il prodotto aggiornato abbia i nuovi valori
	    Assertions.assertThat(prodottoAggiornato).isNotNull();
	    Assertions.assertThat(prodottoAggiornato.getTitolo()).isEqualTo("ProdottoAggiornato");
	    Assertions.assertThat(prodottoAggiornato.getPrezzo()).isEqualTo(15.99);
	    Assertions.assertThat(prodottoAggiornato.getQuantita()).isEqualTo(10);
	}
	
	@Test
	@Order(4)
	public void delete() {

	    //Recupera un prodotto esistente per eliminarlo
	    ResponseList<ProdottoDTO> rL = prodC.list(null, "ProdottoDaEliminare", null, null, null, null, null, null);
	    
	    //Verifica che la lista sia popolata
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova il prodotto da eliminare
	    ProdottoDTO prodottoDaEliminare = rL.getDati().stream()
	        .findFirst()
	        .orElse(null);

	    //Verifica che il prodotto esista
	    Assertions.assertThat(prodottoDaEliminare).isNotNull();

	    //Crea la richiesta di eliminazione
	    ProdottoRequest reqP = new ProdottoRequest();
	    reqP.setId(prodottoDaEliminare.getId());

	    //Esegui la chiamata di eliminazione
	    ResponseEntity<ResponseBase> rB = prodC.delete(reqP);

	    //Estrai il corpo della risposta
	    //Perchè nel controller è stato usato ResponseEntity<ResponseBase>
	    ResponseBase response = rB.getBody();

	    //Verifica che la risposta non sia nulla
	    Assertions.assertThat(response).isNotNull();

	    //Verifica che la chiamata sia andata a buon fine
	    Assertions.assertThat(response.getRc()).isTrue();

	    //Controlla che il prodotto non esista più nella lista
	    ResponseList<ProdottoDTO> rLAfterDelete = prodC.list(null, "ProdottoBalordo", null, null, null, null, null, null);

	    Assertions.assertThat(rLAfterDelete.getDati()).doesNotContain(prodottoDaEliminare);
	}

}

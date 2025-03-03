package com.betacom.cz.controller;

import java.util.List;
import java.util.stream.Collectors;
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
import com.betacom.cz.models.Immagine;
import com.betacom.cz.repositories.IImmagineRepository;
import com.betacom.cz.request.ProdottoRequest;
import com.betacom.cz.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoControllerTest {
	
	@Autowired
	ProdottoController prodC;
	
	@Autowired
	IImmagineRepository imgR;
	
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

	    MockMultipartFile mockImage1 = new MockMultipartFile(
	        "file", "test1.jpg", "image/jpeg", "fake image content".getBytes()
	    );

	    MultipartFile[] immaginiArray1 = { mockImage1 };
	    reqP1.setImmagini(immaginiArray1); 

	    ResponseEntity<ResponseBase> rE1 = prodC.create(reqP1);
	    ResponseBase rB1 = rE1.getBody();

	    Assertions.assertThat(rB1).isNotNull();
	    Assertions.assertThat(rB1.getRc()).isTrue();

	    List<Immagine> immaginiSalvate = imgR.findAll();
	    System.out.println("Numero di immagini salvate: " + immaginiSalvate.size());

	    for (Immagine img : immaginiSalvate) {
	        System.out.println("Immagine salvata: ID = " + img.getId() + ", Nome = " + img.getNomeImmagine());
	    }

	    Assertions.assertThat(immaginiSalvate).isNotEmpty();
	    Assertions.assertThat(immaginiSalvate.get(0).getNomeImmagine()).isEqualTo("test1.jpg");

	    ProdottoRequest reqP2 = new ProdottoRequest();        
	    reqP2.setPrezzo(14.99);
	    reqP2.setQuantita(5);
	    reqP2.setTitolo("ProdottoDaEliminare");
	    reqP2.setAnimaleID(1);
	    reqP2.setMarcaID(2);
	    reqP2.setTipologiaID(2);
	    reqP2.setDescrizione("Prodotto da eliminare");

	    MockMultipartFile mockImage2 = new MockMultipartFile(
	        "file", "test2.jpg", "image/jpeg", "another fake image content".getBytes()
	    );

	    MultipartFile[] immaginiArray2 = { mockImage2 };
	    reqP2.setImmagini(immaginiArray2); 

	    ResponseEntity<ResponseBase> rE2 = prodC.create(reqP2);
	    ResponseBase rB2 = rE2.getBody();

	    Assertions.assertThat(rB2).isNotNull();
	    Assertions.assertThat(rB2.getRc()).isTrue();

	    List<Immagine> immaginiDopoSecondoSalvataggio = imgR.findAll();
	    System.out.println("Numero di immagini dopo secondo salvataggio: " + immaginiDopoSecondoSalvataggio.size());

	    List<String> nomiImmaginiSalvate = immaginiDopoSecondoSalvataggio.stream()
	        .map(Immagine::getNomeImmagine)
	        .collect(Collectors.toList());

	    Assertions.assertThat(nomiImmaginiSalvate).containsExactlyInAnyOrder("test1.jpg", "test2.jpg");
	}

	

	
	@Test
	@Order(2)
	public void listByFilter() {
	    
	    ResponseEntity<List<ProdottoDTO>> rE = prodC.list(null, "ProdottoBalordo", null, null, null, null, null, null, null);

	    Assertions.assertThat(rE.getStatusCode().is2xxSuccessful()).isTrue();
	    
	    List<ProdottoDTO> prodotti = rE.getBody();

	    Assertions.assertThat(prodotti).isNotNull();

	    Assertions.assertThat(prodotti).isNotEmpty();

	    ProdottoDTO prodottoTrovato = prodotti.stream().findFirst().orElse(null);

	    Assertions.assertThat(prodottoTrovato).isNotNull();

	    Assertions.assertThat(prodottoTrovato.getTitolo()).isEqualTo("ProdottoBalordo");
	    Assertions.assertThat(prodottoTrovato.getPrezzo()).isEqualTo(9.99);
	    Assertions.assertThat(prodottoTrovato.getQuantita()).isEqualTo(3);
	}
	
	@Test
	@Order(3)
	public void update() {

	    ResponseEntity<List<ProdottoDTO>> rE = prodC.list(null, "ProdottoBalordo", null, null, null, null, null, null, null);
	    
	    Assertions.assertThat(rE.getStatusCode().is2xxSuccessful()).isTrue();

	    List<ProdottoDTO> prodotti = rE.getBody();

	    Assertions.assertThat(prodotti).isNotNull().isNotEmpty();

	    ProdottoDTO prodottoDaAggiornare = prodotti.stream().findFirst().orElse(null);

	    Assertions.assertThat(prodottoDaAggiornare).isNotNull();

	    ProdottoRequest reqP = new ProdottoRequest();
	    reqP.setId(prodottoDaAggiornare.getId());
	    reqP.setPrezzo(15.99);
	    reqP.setQuantita(10);
	    reqP.setTitolo("ProdottoAggiornato");
	    reqP.setAnimaleID(prodottoDaAggiornare.getAnimale().getId());
	    reqP.setMarcaID(prodottoDaAggiornare.getMarca().getId());
	    reqP.setTipologiaID(prodottoDaAggiornare.getTipologia().getId());
	    reqP.setDescrizione("Nuova descrizione aggiornata");

	    ResponseEntity<ResponseBase> updateResponse = prodC.update(reqP);

	    Assertions.assertThat(updateResponse.getStatusCode().is2xxSuccessful()).isTrue();

	    ResponseBase responseBody = updateResponse.getBody();
	    Assertions.assertThat(responseBody).isNotNull();
	    Assertions.assertThat(responseBody.getRc()).isTrue();

	    ResponseEntity<List<ProdottoDTO>> updatedResponseEntity = prodC.list(null, "ProdottoAggiornato", null, null, null, null, null, null, null);

	    Assertions.assertThat(updatedResponseEntity.getStatusCode().is2xxSuccessful()).isTrue();

	    List<ProdottoDTO> prodottiAggiornati = updatedResponseEntity.getBody();

	    Assertions.assertThat(prodottiAggiornati).isNotNull().isNotEmpty();

	    ProdottoDTO prodottoAggiornato = prodottiAggiornati.stream().findFirst().orElse(null);

	    Assertions.assertThat(prodottoAggiornato).isNotNull();
	    Assertions.assertThat(prodottoAggiornato.getTitolo()).isEqualTo("ProdottoAggiornato");
	    Assertions.assertThat(prodottoAggiornato.getPrezzo()).isEqualTo(15.99);
	    Assertions.assertThat(prodottoAggiornato.getQuantita()).isEqualTo(10);	
	}
	
	@Test
	@Order(4)
	public void delete() {

	    ResponseEntity<List<ProdottoDTO>> responseEntity = prodC.list(null, "ProdottoDaEliminare", null, null, null, null, null, null, null);

	    Assertions.assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();

	    List<ProdottoDTO> prodotti = responseEntity.getBody();

	    Assertions.assertThat(prodotti).isNotNull().isNotEmpty();

	    ProdottoDTO prodottoDaEliminare = prodotti.stream().findFirst().orElse(null);

	    Assertions.assertThat(prodottoDaEliminare).isNotNull();

	    ProdottoRequest reqP = new ProdottoRequest();
	    reqP.setId(prodottoDaEliminare.getId());

	    ResponseEntity<ResponseBase> deleteResponse = prodC.delete(reqP);

	    ResponseBase responseBody = deleteResponse.getBody();

	    Assertions.assertThat(responseBody).isNotNull();

	    Assertions.assertThat(responseBody.getRc()).isTrue();

	    ResponseEntity<List<ProdottoDTO>> responseAfterDelete = prodC.list(null, "ProdottoBalordo", null, null, null, null, null, null, null);

	    List<ProdottoDTO> prodottiDopoCancellazione = responseAfterDelete.getBody();

	    Assertions.assertThat(prodottiDopoCancellazione).doesNotContain(prodottoDaEliminare);
	}
	
	@Test
	@Order(5)
	public void createBis() {
	    ProdottoRequest reqP1 = new ProdottoRequest();        
	    reqP1.setPrezzo(9.99);
	    reqP1.setQuantita(3);
	    reqP1.setTitolo("ProdottoBalordo");
	    reqP1.setAnimaleID(1);
	    reqP1.setMarcaID(2);
	    reqP1.setTipologiaID(2);
	    reqP1.setDescrizione("Prodotto bello");

	    MockMultipartFile mockImage1 = new MockMultipartFile(
	        "file", "test1.jpg", "image/jpeg", "fake image content".getBytes()
	    );

	    MultipartFile[] immaginiArray1 = { mockImage1 };
	    reqP1.setImmagini(immaginiArray1); 

	    ResponseEntity<ResponseBase> rE1 = prodC.create(reqP1);
	    ResponseBase rB1 = rE1.getBody();

	    Assertions.assertThat(rB1).isNotNull();
	    Assertions.assertThat(rB1.getRc()).isTrue();

	    List<Immagine> immaginiSalvate = imgR.findAll();
	    System.out.println("Numero di immagini salvate: " + immaginiSalvate.size());

	    for (Immagine img : immaginiSalvate) {
	        System.out.println("Immagine salvata: ID = " + img.getId() + ", Nome = " + img.getNomeImmagine());
	    }

	    Assertions.assertThat(immaginiSalvate).isNotEmpty();
	    Assertions.assertThat(immaginiSalvate.get(0).getNomeImmagine()).isEqualTo("test1.jpg");
	}




}

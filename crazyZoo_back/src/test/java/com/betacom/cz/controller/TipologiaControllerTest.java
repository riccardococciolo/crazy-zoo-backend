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

	    //Ordina per ID crescente 
	    List<TipologiaDTO> rLsort = rL.getDati().stream()
	                              .sorted(Comparator.comparing(TipologiaDTO::getId)) // Ordina per ID crescente
	                              .toList(); 

	    Assertions.assertThat(rLsort.get(0).getNome()).isEqualTo("Cibo secco");  
	}
	
	@Test
	@Order(3)
	public void update() {
		
	    //Recupera tutte le tipologie
	    ResponseList<TipologiaDTO> rL = tipologiaC.listAll();
	    
	    //Verifica che la lista sia popolata
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova la tipologia da aggiornare
	    TipologiaDTO tipologiaDaAggiornare = rL.getDati().stream()
	        .filter(m -> "Cibo umido".equals(m.getNome()))
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che esista la tipologia
	    Assertions.assertThat(tipologiaDaAggiornare).isNotNull();
	    
	    //Crea una richiesta di update con l'ID corretto
	    TipologiaRequest reqT = new TipologiaRequest();
	    reqT.setId(tipologiaDaAggiornare.getId()); 
	    reqT.setNome("ProvaUpdate"); 
	    
	    //Esegui l'update
	    ResponseBase uRB = tipologiaC.update(reqT);
	    
	    //Verifica che l'update sia andato a buon fine
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    //Recupera nuovamente tutte le marche per verificare l'aggiornamento
	    ResponseList<TipologiaDTO> uRL = tipologiaC.listAll();
	    
	    Assertions.assertThat(uRL.getRc()).isTrue();

	    //Cerca la tipologia aggiornata con il nuovo nome
	    TipologiaDTO tipologiaAggiornata = uRL.getDati().stream()
	        .filter(m -> "ProvaUpdate".equals(m.getNome()))
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che la tipologia aggiornata esista e abbia il nome corretto
	    Assertions.assertThat(tipologiaAggiornata).isNotNull();
	    Assertions.assertThat(tipologiaAggiornata.getNome()).isEqualTo("ProvaUpdate");
	}
	
	@Test
	@Order(4)
	public void delete() {
	    
	    //Recupera tutte le tipologie
	    ResponseList<TipologiaDTO> rL = tipologiaC.listAll();
	    
	    //Verifica che la lista sia popolata
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova la tipologia da eliminare
	    TipologiaDTO tipologiaDaEliminare = rL.getDati().stream()
	        .filter(t -> "Cibo secco".equals(t.getNome())) 
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che esista la tipologia
	    Assertions.assertThat(tipologiaDaEliminare).isNotNull();
	    
	    //Crea la richiesta di eliminazione
	    TipologiaRequest reqT = new TipologiaRequest();
	    reqT.setId(tipologiaDaEliminare.getId()); 
	    
	    //Esegui il delete
	    ResponseBase uRB = tipologiaC.delete(reqT);
	    
	    //Verifica che il delete sia andato a buon fine
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    //Recupera nuovamente tutte le tipologie per verificare che sia stata eliminata
	    ResponseList<TipologiaDTO> uRL = tipologiaC.listAll();
	    
	    Assertions.assertThat(uRL.getRc()).isTrue();

	    //Cerca la tipologia eliminata
	    TipologiaDTO tipologiaEliminata = uRL.getDati().stream()
	        .filter(t -> "Cibo secco".equals(t.getNome())) 
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che la tipologia eliminata NON esista più
	    Assertions.assertThat(tipologiaEliminata).isNull();
	}

}

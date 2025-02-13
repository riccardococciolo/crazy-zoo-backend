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
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;
import com.betacom.cz.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MarcaControllerTest {
	
	@Autowired
	MarcaController marcaC;
	
	@Test
	@Order(1)
	public void create() {
		
		MarcaRequest reqMarca = new MarcaRequest();		
		reqMarca.setNome("Royal Canin");	
		ResponseBase rB = marcaC.create(reqMarca);
		
		MarcaRequest reqMarca2 = new MarcaRequest();
		reqMarca2.setNome("Purina One");
		ResponseBase rB2 = marcaC.create(reqMarca2);
			
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
		Assertions.assertThat(rB2.getRc()).isEqualTo(true);	
	}
	
	@Test
	@Order(2)
	public void listAll() {
	    
	    ResponseList<MarcaDTO> rL = marcaC.listAll();    
	    Assertions.assertThat(rL.getRc()).isEqualTo(true);    

	    //Ordina per ID crescente 
	    List<MarcaDTO> rLsort = rL.getDati().stream()
	                              .sorted(Comparator.comparing(MarcaDTO::getId)) // Ordina per ID crescente
	                              .toList(); 

	    Assertions.assertThat(rLsort.get(0).getNome()).isEqualTo("Royal Canin");  
	}
	
	@Test
	@Order(3)
	public void listByID() {
		ResponseObject<MarcaDTO> rObj = marcaC.listByID(1);
		
		Assertions.assertThat(rObj.getRc()).isEqualTo(true);
		Assertions.assertThat(rObj).isNotNull();
	}
	
	@Test
	@Order(4)
	public void update() {
		
	    //Recupera tutte le marche
	    ResponseList<MarcaDTO> rL = marcaC.listAll();
	    
	    //Verifica che la lista sia popolata
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova la marca da aggiornare
	    MarcaDTO marcaDaAggiornare = rL.getDati().stream()
	        .filter(m -> "Purina One".equals(m.getNome()))
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che esista la marca
	    Assertions.assertThat(marcaDaAggiornare).isNotNull();
	    
	    //Crea una richiesta di update con l'ID corretto
	    MarcaRequest reqMarca = new MarcaRequest();
	    reqMarca.setId(marcaDaAggiornare.getId()); 
	    reqMarca.setNome("ProvaUpdate"); 
	    
	    //Esegui l'update
	    ResponseBase uRB = marcaC.update(reqMarca);
	    
	    //Verifica che l'update sia andato a buon fine
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    //Recupera nuovamente tutte le marche per verificare l'aggiornamento
	    ResponseList<MarcaDTO> uRL = marcaC.listAll();
	    
	    Assertions.assertThat(uRL.getRc()).isTrue();

	    //Cerca la marca aggiornata con il nuovo nome
	    MarcaDTO marcaAggiornata = uRL.getDati().stream()
	        .filter(m -> "ProvaUpdate".equals(m.getNome()))
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che la marca aggiornata esista e abbia il nome corretto
	    Assertions.assertThat(marcaAggiornata).isNotNull();
	    Assertions.assertThat(marcaAggiornata.getNome()).isEqualTo("ProvaUpdate");
	}
	
	@Test
	@Order(5)
	public void delete() {
	    
	    //Recupera tutte le marche
	    ResponseList<MarcaDTO> rL = marcaC.listAll();
	    
	    //Verifica che la lista sia popolata
	    Assertions.assertThat(rL.getRc()).isTrue();
	    Assertions.assertThat(rL.getDati()).isNotEmpty();

	    //Trova la marca da eliminare
	    MarcaDTO marcaDaEliminare = rL.getDati().stream()
	        .filter(m -> "Royal Canin".equals(m.getNome())) 
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che esista la marca
	    Assertions.assertThat(marcaDaEliminare).isNotNull();
	    
	    //Crea la richiesta di eliminazione
	    MarcaRequest reqMarca = new MarcaRequest();
	    reqMarca.setId(marcaDaEliminare.getId()); 
	    
	    //Esegui il delete
	    ResponseBase uRB = marcaC.delete(reqMarca);
	    
	    //Verifica che il delete sia andato a buon fine
	    Assertions.assertThat(uRB.getRc()).isTrue();

	    //Recupera nuovamente tutte le marche per verificare che sia stata eliminata
	    ResponseList<MarcaDTO> uRL = marcaC.listAll();
	    
	    Assertions.assertThat(uRL.getRc()).isTrue();

	    //Cerca la marca eliminata
	    MarcaDTO marcaEliminata = uRL.getDati().stream()
	        .filter(m -> "Royal Canin".equals(m.getNome())) 
	        .findFirst()
	        .orElse(null);
	    
	    //Verifica che la marca eliminata NON esista più
	    Assertions.assertThat(marcaEliminata).isNull();
	}

}

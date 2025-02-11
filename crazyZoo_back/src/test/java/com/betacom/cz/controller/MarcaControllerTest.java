package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.controller.MarcaController;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.response.ResponseBase;
import com.betacom.cz.response.ResponseList;


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
		Assertions.assertThat(rL.getDati().get(1).getNome()).isEqualTo("Royal Canin");	
	}
	
	

}

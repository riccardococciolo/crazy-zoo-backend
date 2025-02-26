package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RicevutaControllerTest {
	
	@Autowired
	RicevutaController ricevutaC;
	
	@Test
	public void send() {
		
		ResponseBase rB = ricevutaC.send(2);
		
        Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
	}

}

package com.betacom.cz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.cz.request.MailRequest;
import com.betacom.cz.response.ResponseBase;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MailControllerTest {
	
	@Autowired
	MailController mailC;
	
	@Test
	public void send() {
		
		MailRequest mailReq = new MailRequest("dario.zancuo@hotmail.it", "ProvaJUnit", "Questo è un test JUnit", null);
		
		ResponseBase rB = mailC.send(mailReq);
		
        Assertions.assertThat(rB).isNotNull();
		Assertions.assertThat(rB.getRc()).isEqualTo(true);	
	}

}

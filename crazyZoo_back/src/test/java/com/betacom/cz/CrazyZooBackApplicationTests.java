package com.betacom.cz;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.controller.AnimaliControllerTest;
import com.betacom.cz.controller.AuthControllerTest;
import com.betacom.cz.controller.CarrelliControllerTest;
import com.betacom.cz.controller.MailControllerTest;
import com.betacom.cz.controller.MarcaControllerTest;
import com.betacom.cz.controller.OrdineControllerTest;
import com.betacom.cz.controller.ProdottiCarrelloControllerTest;
import com.betacom.cz.controller.ProdottoControllerTest;
import com.betacom.cz.controller.ProdottoOrdineControllerTest;
import com.betacom.cz.controller.RecensioneControllerTest;
import com.betacom.cz.controller.RicevutaControllerTest;
import com.betacom.cz.controller.TipologiaControllerTest;
import com.betacom.cz.controller.UtenteControllerTest;

@Suite
@SelectClasses({
			  	AnimaliControllerTest.class,
			  	MarcaControllerTest.class,
			  	TipologiaControllerTest.class,
			  	ProdottoControllerTest.class,
			  	UtenteControllerTest.class,
			  	RecensioneControllerTest.class,
			  	CarrelliControllerTest.class,
			  	ProdottiCarrelloControllerTest.class,
			  	OrdineControllerTest.class,
			  	ProdottoOrdineControllerTest.class,		  	
			  	DTOTest.class,
				AuthControllerTest.class,
				MailControllerTest.class,
				RicevutaControllerTest.class,
			  	ConstructorTest.class
			  })

@SpringBootTest
class CrazyZooBackApplicationTests {

	@Test
	void contextLoads() {
	}

}

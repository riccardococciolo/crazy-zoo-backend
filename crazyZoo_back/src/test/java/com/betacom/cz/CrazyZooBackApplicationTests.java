package com.betacom.cz;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.controller.AnimaliControllerTest;
import com.betacom.cz.controller.MarcaControllerTest;
import com.betacom.cz.controller.TipologiaControllerTest;
import com.betacom.cz.controller.UtenteControllerTest;

@Suite
@SelectClasses({
			  	AnimaliControllerTest.class,
			  	MarcaControllerTest.class,
			  	TipologiaControllerTest.class,
			  	UtenteControllerTest.class
			  })

@SpringBootTest
class CrazyZooBackApplicationTests {

	@Test
	void contextLoads() {
	}

}

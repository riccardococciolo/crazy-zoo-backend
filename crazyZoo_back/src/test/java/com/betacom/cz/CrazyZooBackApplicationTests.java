package com.betacom.cz;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.controller.AnimaliControllerTest;
import com.betacom.cz.controller.ImmagineController;
import com.betacom.cz.controller.MarcaController;
import com.betacom.cz.controller.ProdottoController;
import com.betacom.cz.controller.TipologiaController;

@Suite
@SelectClasses({
			  	AnimaliControllerTest.class,
			  	TipologiaController.class,
			  	MarcaController.class,
			  	ProdottoController.class,
			  	ImmagineController.class		  	
			  })

@SpringBootTest
class CrazyZooBackApplicationTests {

	@Test
	void contextLoads() {
	}

}

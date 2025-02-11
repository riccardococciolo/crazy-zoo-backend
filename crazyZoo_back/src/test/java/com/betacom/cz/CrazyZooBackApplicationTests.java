package com.betacom.cz;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import com.betacom.cz.controller.AnimaliControllerTest;

@Suite
@SelectClasses({
			  	AnimaliControllerTest.class
			  	
			  })

@SpringBootTest
class CrazyZooBackApplicationTests {

	@Test
	void contextLoads() {
	}

}

package com.betacom.cz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.betacom.cz.request.CarrelloRequest;

public class ConstructorTest {

	@Test
	void testCarrelloRequestConstructor() {
		CarrelloRequest carrelloRequest = new CarrelloRequest(1, 100);

		Assertions.assertThat(carrelloRequest.getId()).isEqualTo(1);
		Assertions.assertThat(carrelloRequest.getUtenteID()).isEqualTo(100);
	}

}

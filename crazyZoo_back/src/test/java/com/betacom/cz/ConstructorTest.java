package com.betacom.cz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.betacom.cz.request.CarrelloRequest;

public class ConstructorTest {

	@Test
	void testCarrelloRequestConstructor() {
		// Creazione di un oggetto CarrelloRequest utilizzando il costruttore
		CarrelloRequest carrelloRequest = new CarrelloRequest(1, 100);

		// Verifica che i valori siano stati impostati correttamente
		Assertions.assertThat(carrelloRequest.getId()).isEqualTo(1);
		Assertions.assertThat(carrelloRequest.getUtenteID()).isEqualTo(100);
	}

}

package com.betacom.cz.dto;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DTOTest {

	@Test
	void testProdottoDTOGettersAndSetters() {
		ProdottoDTO prodotto = new ProdottoDTO();

		prodotto.setId(1);
		prodotto.setPrezzo(19.99);
		prodotto.setQuantita(10);
		prodotto.setTitolo("Cibo per cani");
		prodotto.setAnimale(new AnimaleDTO(1, "Cane"));
		prodotto.setMarca(new MarcaDTO(1, "Royal Canin"));
		prodotto.setTipologia(new TipologiaDTO(1, "Secco"));
		prodotto.setDescrizione("Descrizione del prodotto");

		Assertions.assertThat(prodotto.getId()).isEqualTo(1);
	}

	@Test
	void testAnimaleDTOGettersAndSetters() {
		AnimaleDTO animale = new AnimaleDTO();
		animale.setId(2);
		animale.setNome("Gatto");

		Assertions.assertThat(animale.getId()).isEqualTo(2);
	}

	@Test
	void testMarcaDTOGettersAndSetters() {
		MarcaDTO marca = new MarcaDTO();
		marca.setId(3);
		marca.setNome("Purina");

		Assertions.assertThat(marca.getId()).isEqualTo(3);
	}

	@Test
	void testTipologiaDTOGettersAndSetters() {
		TipologiaDTO tipologia = new TipologiaDTO();
		tipologia.setId(4);
		tipologia.setNome("Umido");

		Assertions.assertThat(tipologia.getId()).isEqualTo(4);
	}

	@Test
	void testUtenteDTOGettersAndSetters() {
		UtenteDTO utente = new UtenteDTO();
		utente.setId(5);
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		utente.setUsername("mrossi");
		utente.setEmail("mrossi@example.com");
		utente.setCellulare("1234567890");
		utente.setRuolo("Admin");

		Assertions.assertThat(utente.getId()).isEqualTo(5);
		Assertions.assertThat(utente.getNome()).isEqualTo("Mario");
		Assertions.assertThat(utente.getCognome()).isEqualTo("Rossi");
		Assertions.assertThat(utente.getUsername()).isEqualTo("mrossi");
		Assertions.assertThat(utente.getEmail()).isEqualTo("mrossi@example.com");
		Assertions.assertThat(utente.getCellulare()).isEqualTo("1234567890");
		Assertions.assertThat(utente.getRuolo()).isEqualTo("Admin");
	}

	@Test
	void testRecensioneDTOGettersAndSetters() {
		RecensioneDTO recensione = new RecensioneDTO();
		recensione.setId(6);
		recensione.setValutazione(5);
		recensione.setDescrizione("Ottimo prodotto");
		recensione.setProdotto(new ProdottoDTO());
		recensione.setUtente(new UtenteDTO());

		Assertions.assertThat(recensione.getId()).isEqualTo(6);
		Assertions.assertThat(recensione.getValutazione()).isEqualTo(5);
		Assertions.assertThat(recensione.getDescrizione()).isEqualTo("Ottimo prodotto");
		Assertions.assertThat(recensione.getProdotto()).isNotNull();
		Assertions.assertThat(recensione.getUtente()).isNotNull();
	}

	@Test
	void testOrdineDTOGettersAndSetters() {
		OrdineDTO ordine = new OrdineDTO();
		ordine.setId(7);
		ordine.setCarrello(new CarrelloDTO());
		ordine.setUtente(new UtenteDTO());
		List<ProdottoDTO> prodotti = Arrays.asList(new ProdottoDTO());
		ordine.setProdotti(prodotti);

		Assertions.assertThat(ordine.getId()).isEqualTo(7);
		Assertions.assertThat(ordine.getCarrello()).isNotNull();
		Assertions.assertThat(ordine.getUtente()).isNotNull();
		Assertions.assertThat(ordine.getProdotti()).isEqualTo(prodotti);
	}



}

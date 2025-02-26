package com.betacom.cz.services.implementations;

import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.cz.models.Ordine;
import com.betacom.cz.repositories.IOrdineRepository;
import com.betacom.cz.request.MailRequest;
import com.betacom.cz.services.interfaces.MailServices;
import com.betacom.cz.services.interfaces.RicevutaServices;

@Service
public class RicevutaImplementation implements RicevutaServices {
	
	@Autowired
	MailServices mailS;
	
	@Autowired
	IOrdineRepository ordineR;
	
	@Autowired
	Logger log;

	@Override
	public void sendRicevuta(Integer id) throws Exception {
		
		Optional<Ordine> ordine = ordineR.findById(id);
		
		if (ordine.isEmpty())
			throw new Exception("Ordine non trovato");
		
		if (ordine.get().getUtente().getEmail() == null)
			throw new Exception("Utente senza mail.");

		StringBuilder body = new StringBuilder();	
		body.append("<h1>Crazy Zoo</h1><br>")
		.append("<br><br>Buongiorno, Egregio/a Singor/a ")
		.append(ordine.get().getUtente().getNome())
		.append(",<br>")
		.append("in allegato potete trova la ricevuto <br>")
		.append("Il Team Crazy Zoo");

		StringBuilder attachment = new StringBuilder();
		attachment.append("<h1>Ricevuta Crazy Zoo</h1><br>")
		.append("</strong> Utente <strong>")
		.append(ordine.get().getUtente().getNome())
		.append(" ")
		.append(ordine.get().getUtente().getCognome())
		.append("</strong><br><br>Prodotto       Prezzo <br>");
		ordine.get().getProdotti().forEach(p -> {
		attachment.append(p.getTitolo())
		.append("       ")
		.append(p.getPrezzo())
		.append("<br>");
		});

		attachment.append("<br><br>  TOTALE    : <strong>")
		.append(ordine.get().getTotale())
		.append("</strong>");

		MailRequest req = new MailRequest(ordine.get().getUtente().getEmail(), "ricevuta Crazy Zoo", body.toString(), attachment.toString());


		mailS.sendMailWithAttachment(req);
	}

}

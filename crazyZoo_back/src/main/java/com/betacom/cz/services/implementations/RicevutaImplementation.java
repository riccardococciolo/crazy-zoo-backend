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
		body.append("<h1 style='color:#4CAF50;'>Crazy Zoo - Conferma Ordine</h1>")
		    .append("<p>Gentile <strong>")
		    .append(ordine.get().getUtente().getNome())
		    .append(" ")
		    .append(ordine.get().getUtente().getCognome())
		    .append("</strong>,</p>")
		    .append("<p>La ringraziamo per il suo acquisto presso Crazy Zoo. In allegato troverà la ricevuta dettagliata del suo ordine.</p>")
		    .append("<p>Di seguito un riepilogo del suo ordine:</p>");

		StringBuilder attachment = new StringBuilder();
		attachment.append("<h2 style='color:#4CAF50;'>Ricevuta d'Acquisto</h2>")
		    .append("<p><strong>Nome Cliente:</strong> ")
		    .append(ordine.get().getUtente().getNome())
		    .append(" ")
		    .append(ordine.get().getUtente().getCognome())
		    .append("</p>")
		    .append("<table style='width:100%; border-collapse: collapse;'>")
		    .append("<tr style='background-color:#f2f2f2;'><th style='border: 1px solid #ddd; padding: 8px; text-align:left;'>Prodotto</th>")
		    .append("<th style='border: 1px solid #ddd; padding: 8px; text-align:left;'>Prezzo</th></tr>");

		ordine.get().getProdotti().forEach(p -> {
		    attachment.append("<tr><td style='border: 1px solid #ddd; padding: 8px;'>")
		        .append(p.getTitolo())
		        .append("</td><td style='border: 1px solid #ddd; padding: 8px;'>€ ")
		        .append(p.getPrezzo())
		        .append("</td></tr>");
		});

		attachment.append("</table>")
		    .append("<p><strong>Totale Ordine: </strong>€ ")
		    .append(ordine.get().getTotale())
		    .append("</p>")
		    .append("<br><p>Grazie per aver scelto Crazy Zoo! Restiamo a sua disposizione per qualsiasi necessità.</p>")
		    .append("<p>Cordiali saluti,</p>")
		    .append("<p><strong>Il Team Crazy Zoo</strong></p>");

		MailRequest req = new MailRequest(ordine.get().getUtente().getEmail(), 
		    "Ricevuta d'Acquisto - Crazy Zoo", 
		    body.toString(), 
		    attachment.toString());

		mailS.sendMailWithAttachment(req);
	}

}

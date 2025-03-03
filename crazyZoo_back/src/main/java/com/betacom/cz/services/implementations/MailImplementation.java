package com.betacom.cz.services.implementations;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.betacom.cz.models.Utente;
import com.betacom.cz.repositories.IUtenteRepository;
import com.betacom.cz.request.MailRequest;
import com.betacom.cz.services.interfaces.MailServices;
import com.betacom.cz.services.interfaces.PDFGeneratorServices;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class MailImplementation implements MailServices {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	IUtenteRepository utenteR;
	
	@Autowired
	PDFGeneratorServices pdf;
	
	@Autowired
	Logger log;
	
	@Override
	@Async
	public void sendMail(MailRequest req) throws Exception {
		log.debug("sendMail :" + req);

		if (req.getTo() == null || req.getOggetto() == null || req.getBody() == null)
		        throw new Exception("To, Oggetto e Body sono obbligatori");

		    MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		    helper.setTo(req.getTo());
		    helper.setFrom("crazyzooapp@zohomail.eu"); 
		    helper.setSubject(req.getOggetto());
		    helper.setText(req.getBody(), true); 

		    mailSender.send(mimeMessage);
		
	}

	@Override
	public void sendMailWithAttachment(MailRequest req) throws Exception {
		log.debug("sendMailWithAttachment :" + req);
		
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		byte[] attachment = pdf.generatePDF("test.pdt", req.getAttachment());

		    helper.setTo(req.getTo());
		    helper.setFrom("crazyzooapp@zohomail.eu"); 
		    helper.setSubject(req.getOggetto());
		    helper.setText(req.getBody(), true); 

		    helper.addAttachment("ricevuta.pdf", new ByteArrayDataSource(attachment, "application/pdf"));

		   
		    
		    mailSender.send(mimeMessage);		
	}

	@Override
	public void registerMail(MailRequest req) throws Exception {
	    
	    Optional<Utente> uO = utenteR.findByEmail(req.getTo());

	    if (uO.isPresent()) {
	        Utente utente = uO.get();
	        StringBuilder sb = new StringBuilder();
	        sb.append("<html><body>")
	          .append("<p>Ciao <b>").append(utente.getNome()).append("</b>,</p>")
	          .append("<p>Siamo felicissimi di darti il benvenuto su <b>CrazyZooApp</b>! 🎉</p>")
	          .append("<p>La tua registrazione è avvenuta con successo e ora puoi accedere a tutte le nostre funzionalità esclusive.</p>")
	          .append("<p>Esplora, scopri e divertiti con i nostri servizi pensati apposta per te.</p>")
	          .append("<h3>✨ Cosa puoi fare ora?</h3>")
	          .append("<ul>")
	          .append("<li>✅ Accedere alla tua area personale</li>")
	          .append("<li>✅ Scoprire contenuti esclusivi</li>")
	          .append("<li>✅ Ricevere aggiornamenti e offerte speciali</li>")
	          .append("</ul>")
	          .append("<p>Se hai domande o hai bisogno di supporto, il nostro team è sempre a tua disposizione.</p>")
	          .append("<p>Scrivici e saremo felici di aiutarti!</p>")
	          .append("<p>Grazie per aver scelto <b>CrazyZooApp</b>. Siamo entusiasti di averti con noi! 🦁🐼🐵</p>")
	          .append("<p>A presto,</p>")
	          .append("<p><b>Il team di CrazyZooApp</b></p>")
	          .append("</body></html>");

	        MailRequest confirmationMail = new MailRequest();
	        confirmationMail.setTo(req.getTo());
	        confirmationMail.setOggetto("🎉 Benvenuto su CrazyZooApp! La tua avventura inizia ora!");
	        confirmationMail.setBody(sb.toString());

	        sendMail(confirmationMail);
	    } else {
	        throw new Exception("Utente non trovato con l'email: " + req.getTo());
	    }
	}




}

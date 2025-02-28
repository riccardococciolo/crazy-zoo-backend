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

		//SimpleMailMessage message = new SimpleMailMessage();

		if (req.getTo() == null || req.getOggetto() == null || req.getBody() == null)
		        throw new Exception("To, Oggetto e Body sono obbligatori");

		    MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		    helper.setTo(req.getTo());
		    helper.setFrom("crazyzooapp@zohomail.eu"); // Deve essere lo stesso del tuo account SMTP
		    helper.setSubject(req.getOggetto());
		    helper.setText(req.getBody(), true); // true -> supporta HTML

		    mailSender.send(mimeMessage);
		
	}

	@Override
	public void sendMailWithAttachment(MailRequest req) throws Exception {
		log.debug("sendMailWithAttachment :" + req);
		
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		byte[] attachment = pdf.generatePDF("test.pdt", req.getAttachment());

		    helper.setTo(req.getTo());
		    helper.setFrom("crazyzooapp@zohomail.eu"); // Deve essere lo stesso del tuo account SMTP
		    helper.setSubject(req.getOggetto());
		    helper.setText(req.getBody(), true); // true -> supporta HTML

		    // FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment)); 
		    helper.addAttachment("ricevuta.pdf", new ByteArrayDataSource(attachment, "application/pdf"));

		   
		    
		    mailSender.send(mimeMessage);		
	}

	@Override
	public void registerMail(MailRequest req) throws Exception {
	    
	    Optional<Utente> uO = utenteR.findByEmail(req.getTo());

	    if (uO.isPresent()) {
	        Utente utente = uO.get();
	        StringBuilder sb = new StringBuilder();
	        sb.append("Ciao ").append(utente.getNome()).append(",\n\n");
	        sb.append("Siamo felicissimi di darti il benvenuto su **CrazyZooApp**! 🎉\n\n");
	        sb.append("La tua registrazione è avvenuta con successo e ora puoi accedere a tutte le nostre funzionalità esclusive. ")
	          .append("Esplora, scopri e divertiti con i nostri servizi pensati apposta per te.\n\n");
	        sb.append("✨ **Cosa puoi fare ora?**\n")
	          .append("✅ Accedere alla tua area personale\n")
	          .append("✅ Scoprire contenuti esclusivi\n")
	          .append("✅ Ricevere aggiornamenti e offerte speciali\n\n");
	        sb.append("Se hai domande o hai bisogno di supporto, il nostro team è sempre a tua disposizione. ")
	          .append("Scrivici e saremo felici di aiutarti!\n\n");
	        sb.append("Grazie per aver scelto **CrazyZooApp**. Siamo entusiasti di averti con noi! 🦁🐼🐵\n\n");
	        sb.append("A presto,\n");
	        sb.append("**Il team di CrazyZooApp**");

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

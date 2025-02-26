package com.betacom.cz.services.implementations;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

}

package com.betacom.cz.services.interfaces;

import com.betacom.cz.request.MailRequest;

public interface MailServices {
	
	void sendMail(MailRequest req) throws Exception;
	void sendMailWithAttachment(MailRequest req) throws Exception;

}

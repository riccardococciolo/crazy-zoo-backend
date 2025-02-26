package com.betacom.cz.request;

public class MailRequest {
	
	private String to;
	private String oggetto;
	private String body;
	private String attachment;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public MailRequest(String to, String oggetto, String body, String attachment) {
		super();
		this.to = to;
		this.oggetto = oggetto;
		this.body = body;
		this.attachment = attachment;
	}
	
	@Override
	public String toString() {
		return "MailRequest [to=" + to + ", oggetto=" + oggetto + ", body=" + body + ", attachment=" + attachment + "]";
	}
	
}

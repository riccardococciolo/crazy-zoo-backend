package com.betacom.cz.services.interfaces;

public interface PDFGeneratorServices {
	
	byte[] generatePDF(String nome, String body) throws Exception;

}

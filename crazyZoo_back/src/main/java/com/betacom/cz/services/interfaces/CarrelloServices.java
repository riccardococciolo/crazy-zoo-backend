package com.betacom.cz.services.interfaces;

import org.springframework.stereotype.Service;

import com.betacom.cz.request.CarrelloRequest;


public interface CarrelloServices {
	void create(CarrelloRequest req) throws Exception;
	
	void delete(CarrelloRequest req) throws Exception;

}

package com.betacom.cz.services.interfaces;

import java.util.List;

import com.betacom.cz.dto.OrdineDTO;
import com.betacom.cz.request.OrdineRequest;

public interface OrdineServices {
	
    void create(OrdineRequest req) throws Exception;  
    void delete(OrdineRequest req) throws Exception;  
    List<OrdineDTO> listAll();         
    OrdineDTO listById(Integer id);  

}

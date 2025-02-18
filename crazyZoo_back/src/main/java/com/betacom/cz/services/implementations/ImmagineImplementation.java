package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.ImmagineDTO;
import com.betacom.cz.models.Immagine;
import com.betacom.cz.repositories.IImmagineRepository;
import com.betacom.cz.request.ImmagineRequest;
import com.betacom.cz.services.interfaces.ImmagineServices;

@Service
public class ImmagineImplementation implements ImmagineServices{
	
	@Autowired
	IImmagineRepository immR;
	
	@Autowired
	Logger log;

	@Override
	public void create(ImmagineRequest req) throws Exception {
		
	}

	@Override
	public void update(ImmagineRequest req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ImmagineRequest req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ImmagineDTO> listAll() {
		
		List<Immagine> lI = immR.findAll();
		
		return lI.stream()
                .map(i -> new ImmagineDTO(
                        i.getId(),
                        i.getNomeImmagine(),
                        i.getTipoFile(),
                        i.getData()
                ))
                .collect(Collectors.toList());
	}

	@Override
	public ImmagineDTO listById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

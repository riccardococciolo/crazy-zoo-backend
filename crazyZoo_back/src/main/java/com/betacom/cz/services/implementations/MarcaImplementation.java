package com.betacom.cz.services.implementations;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.models.Marca;
import com.betacom.cz.repositories.IMarcaRepository;
import com.betacom.cz.request.MarcaRequest;
import com.betacom.cz.services.interfaces.MarcaServices;

@Service
public class MarcaImplementation implements MarcaServices{
	
	@Autowired
	IMarcaRepository marR;
	
	@Autowired
	Logger log;

	@Override
	public void create(MarcaRequest req) {
		
		if(marR.findByNomeMarca(req.getNomeMarca()).isPresent()) {
			log.error("Tentativo di inserire marca duplicata: {}", req.getNomeMarca());
	        throw new IllegalArgumentException("La marca esiste già");
		}
		
		Marca m = new Marca();
		m.setNomeMarca(req.getNomeMarca());
		
		marR.save(m);
		log.debug("Marca '{}' creata con successo", req.getNomeMarca());
	}

	@Override
	public void update(Integer id, MarcaRequest req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MarcaDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.dto.RecensioneDTO;
import com.betacom.cz.dto.UtenteDTO;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.models.Recensione;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.repositories.IRecensioneRepository;
import com.betacom.cz.request.RecensioniRequest;
import com.betacom.cz.services.interfaces.RecensioneServices;
//import static com.betacom.cz.utils.Utilities.mapToProdottoDTO;
@Service
public class RecensioneImplementation implements RecensioneServices{
	
		@Autowired
		IRecensioneRepository recR;
		
		@Autowired
		IProdottoRepository prodR;
		
		@Override
		public void create(RecensioniRequest req) throws Exception {
			
		    Optional<Prodotto> prodotto = prodR.findById(req.getProdottoID());
		    
		    if (prodotto.isEmpty()) {
		        throw new Exception("Prodotto non trovato");
		    }

		    Optional<Recensione> recensioneObj = recR.findById(req.getId());
		    if (recensioneObj.isPresent()) {
		        throw new Exception("Recensione già avvenuta");
		    }

		    Recensione recensione = new Recensione();
		    recensione.setProdotto(prodotto.get());
		    recR.save(recensione);
		}

		@Override
		public void delete(RecensioniRequest req) throws Exception {
			// TODO Auto-generated method stub
			Optional<Recensione> recensioneObj = recR.findById(req.getId());
			 if (recensioneObj.isEmpty()) {
			        throw new Exception("Recensione non esistente");
		    }
			 recR.delete(recensioneObj.get());
			
		}

		@Override
		public List<RecensioneDTO> listByProdotto(Integer id) throws Exception {
//			Optional<Prodotto> prodotto = prodR.findById(id);
//			if (prodotto.isEmpty()) {
//				throw new Exception("Prodotto non trovato");
//			}
//			List<Recensione> recensioni = prodotto.get().getRecensioni();
//			return recensioni.stream()
//					.map(r -> new RecensioneDTO(
//							r.getId(),
//							r.getValutazione(),
//							r.getDescrizione(),
//							(mapToProdottoDTO(r.getProdotto()),
//									(new UtenteDTO(
//											r.getUtente().getId(),
//											r.getUtente().getNome(),
//											r.getUtente().getCognome(),
//											r.getUtente().getUsername(),
//											r.getUtente().getCellulare(),
//											r.getUtente().getEmail(),))
//									)))
//					.collect(Collectors.toList());
			return null;
		}
}

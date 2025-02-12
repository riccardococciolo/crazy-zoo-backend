package com.betacom.cz.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.dto.TipologiaDTO;
import com.betacom.cz.models.Animale;
import com.betacom.cz.models.Immagine;
import com.betacom.cz.models.Marca;
import com.betacom.cz.models.Prodotto;
import com.betacom.cz.models.Tipologia;
import com.betacom.cz.repositories.IAnimaleRepository;
import com.betacom.cz.repositories.IImmagineRepository;
import com.betacom.cz.repositories.IMarcaRepository;
import com.betacom.cz.repositories.IProdottoRepository;
import com.betacom.cz.repositories.ITipologiaRepository;
import com.betacom.cz.request.ProdottoRequest;
import com.betacom.cz.services.interfaces.ProdottoServices;
import static com.betacom.cz.utils.Utilities.buildImmagineDTO;

@Service
public class ProdottoImplementation implements ProdottoServices {
	
	@Autowired
	IProdottoRepository proR;
	
	@Autowired
	IImmagineRepository immR;
	
	@Autowired
	IAnimaleRepository aniR;
	
	@Autowired
	IMarcaRepository marR;
	
	@Autowired
	ITipologiaRepository tipR;
	
	@Autowired
	Logger log;

	@Override
	public void create(ProdottoRequest req) throws Exception {
		
		if (req.getTitolo() == null)
			throw new Exception("Il titolo è necessario");
		
		if (req.getPrezzo() == null)
			throw new Exception("Il prezzo è necessario");
		
		if (req.getQuantita() == null)
			throw new Exception("La quantità è necessaria");
		
		Optional<Animale> animale = aniR.findById(req.getAnimaleID());
		
		Optional<Marca> marca = marR.findById(req.getMarcaID());
	            
	    Optional<Tipologia> tipologia = tipR.findById(req.getTipologiaID());
	            
	    Prodotto prodotto = new Prodotto();
	    prodotto.setAnimale(animale.get());
	    prodotto.setMarca(marca.get());
	    prodotto.setTipologia(tipologia.get());
	    prodotto.setPrezzo(req.getPrezzo());
	    prodotto.setTitolo(req.getTitolo());
	    prodotto.setQuantita(req.getQuantita());
	    prodotto.setDescrizione(req.getDescrizione());
	  
	    if (req.getImmagini() != null) {
            for (MultipartFile img : req.getImmagini()) {
                if (img != null && !img.isEmpty()) {
                    try {
                        Immagine immagine = new Immagine();
                        immagine.setNomeImmagine(img.getOriginalFilename());
                        immagine.setData(img.getBytes());
                        immagine.setTipoFile(img.getContentType());
                        prodotto.addImmagine(immagine);
                        
                    } catch (Exception e) {
                        throw new Exception("Errore nel salvataggio dell'immagine " + img.getOriginalFilename(), e);
                    }
                }
            }
        }
		
		proR.save(prodotto);
		
	}

	@Override
	public void update(ProdottoRequest req) throws Exception {
		Prodotto prodotto = proR.findById(req.getId())
	            .orElseThrow(() -> new Exception("Prodotto non trovato con id: " + req.getId()));
	    
	   
	    prodotto.setTitolo(req.getTitolo());
	    prodotto.setPrezzo(req.getPrezzo());
	    prodotto.setQuantita(req.getQuantita());
	    prodotto.setDescrizione(req.getDescrizione());
	        
	    if (req.getImmagini() == null) {
	       
	        prodotto.getImmagini().clear();
	    } else {
	       
	        prodotto.getImmagini().clear();
	        
	
	        for (MultipartFile file : req.getImmagini()) {
	            if (file != null && !file.isEmpty()) {
	                try {
	                    Immagine nuovaImg = new Immagine();
	                    nuovaImg.setNomeImmagine(file.getOriginalFilename());
	                    nuovaImg.setData(file.getBytes());
	                    nuovaImg.setTipoFile(file.getContentType());
	                   
	                    nuovaImg.setTipoFile(file.getContentType());
	                    
	                   
	                    prodotto.addImmagine(nuovaImg);
	                } catch (Exception e) {
	                    throw new RuntimeException("Errore nel salvataggio della nuova immagine: " 
	                        + file.getOriginalFilename(), e);
	                }
	            }
	        }
	    }
	    
	   
	    proR.save(prodotto);
		
	}

	@Override
	public void delete(ProdottoRequest req) throws Exception {
		Prodotto prodotto = proR.findById(req.getId())
	            .orElseThrow(() -> new Exception("Prodotto non trovato con id: " + req.getId()));
	    
	   
	    proR.delete(prodotto);
		
	}

	@Override
	public ProdottoDTO listById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdottoDTO> list(Integer id, String titolo, Double prezzo, Integer quantita, String nomeAnimale,
			String nomeTipologia, String nomeMarca, String descrizione) {
		List<Prodotto> lP = proR.findByFilter(id, titolo, prezzo, quantita, nomeAnimale, nomeTipologia, nomeMarca, descrizione);
		
		
		return lP.stream()
	            .map(p -> new ProdottoDTO(
	                    p.getId(),
	                    p.getPrezzo(),
	                    p.getQuantita(),
	                    p.getTitolo(),
	                    new AnimaleDTO(
	                    		p.getAnimale().getId(),
	                    		p.getAnimale().getNomeAnimale()),
	                    new MarcaDTO(
	                    		p.getMarca().getId(),
	                    		p.getMarca().getNomeMarca()),
	                    new TipologiaDTO(
	                    		p.getTipologia().getId(),
	                    		p.getTipologia().getNome()),
	                    buildImmagineDTO(p.getImmagini()),
	                    		p.getDescrizione()
	            ))
	            .collect(Collectors.toList());
	}

}

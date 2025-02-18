package com.betacom.cz.utils;

import java.util.List;
import java.util.stream.Collectors;
import com.betacom.cz.dto.AnimaleDTO;
import com.betacom.cz.dto.ImmagineDTO;
import com.betacom.cz.dto.MarcaDTO;
import com.betacom.cz.dto.ProdottoDTO;
import com.betacom.cz.dto.TipologiaDTO;
import com.betacom.cz.models.Immagine;
import com.betacom.cz.models.Prodotto;

public class Utilities {
	
	//Metodo statico per trasformare una lista di Prodotto in una lista di ProdottoDTO
    public static List<ProdottoDTO> mapToProdottoDTOList(List<Prodotto> prodotti) {
        return prodotti.stream()
                .map(h ->mapToProdottoDTO(h))
                .collect(Collectors.toList());
    }

    //Metodo statico per trasformare un singolo Prodotto in un ProdottoDTO
    public static ProdottoDTO mapToProdottoDTO(Prodotto prodotto) {
        return new ProdottoDTO(
                prodotto.getId(),
                prodotto.getPrezzo(),
                prodotto.getQuantita(),
                prodotto.getTitolo(),
                new AnimaleDTO(
                        prodotto.getAnimale().getId(),
                        prodotto.getAnimale().getNomeAnimale()),
                new MarcaDTO(
                        prodotto.getMarca().getId(),
                        prodotto.getMarca().getNomeMarca()),
                new TipologiaDTO(
                        prodotto.getTipologia().getId(),
                        prodotto.getTipologia().getNome()),
                buildImmagineDTO(prodotto.getImmagini()),
                		prodotto.getDescrizione()
                		// Assicurati che buildImmagineDTO sia statico
        );
    }

    public static List<ImmagineDTO> buildImmagineDTO(List<Immagine> lI) {

        return lI.stream()
                .map(i -> new ImmagineDTO(
                        i.getId(),
                        i.getNomeImmagine(),
                        i.getTipoFile(),
                        i.getData()
                ))
                .collect(Collectors.toList());
    }
}

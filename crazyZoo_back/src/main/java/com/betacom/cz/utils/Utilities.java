package com.betacom.cz.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.cz.dto.ImmagineDTO;
import com.betacom.cz.models.Immagine;

public class Utilities {

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

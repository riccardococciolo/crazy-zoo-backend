package com.betacom.cz.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.betacom.cz.models.Prodotto;

@Repository
public interface IProdottoRepository extends JpaRepository<Prodotto, Integer> {
	
	@Query(name = "prodotto.selectByFilter")
    Page<Prodotto> findByFilterPage(
        @Param("id") Integer id,
        @Param("titolo") String titolo,
        @Param("prezzoMin") Double prezzoMin,
        @Param("prezzoMax") Double prezzoMax,
        @Param("quantita") Integer quantita,
        @Param("nomeAnimale") String nomeAnimale,
        @Param("nomeTipologia") String nomeTipologia,
        @Param("nomeMarca") String nomeMarca,
        @Param("descrizione")String descrizione,
        Pageable pageable
    );
	
	@Query(name = "prodotto.selectByFilter")
    List<Prodotto> findByFilter(
        @Param("id") Integer id,
        @Param("titolo") String titolo,
        @Param("prezzoMin") Double prezzoMin,
        @Param("prezzoMax") Double prezzoMax,
        @Param("quantita") Integer quantita,
        @Param("nomeAnimale") String nomeAnimale,
        @Param("nomeTipologia") String nomeTipologia,
        @Param("nomeMarca") String nomeMarca,
        @Param("descrizione")String descrizione
    );
	
}

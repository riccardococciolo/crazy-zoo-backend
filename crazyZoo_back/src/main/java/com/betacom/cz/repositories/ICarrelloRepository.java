package com.betacom.cz.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.betacom.cz.models.Carrello;

@Repository
public interface ICarrelloRepository extends JpaRepository<Carrello, Integer> {
	
    Optional<Carrello> findByUtenteId(Integer utenteId);
}

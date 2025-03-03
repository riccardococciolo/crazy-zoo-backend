package com.betacom.cz.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.betacom.cz.models.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Integer>{
	
	Optional<Utente> findByEmail(String email);
	Optional<Utente> findByUsername(String username);	
	Optional<Utente> findByCellulare(String cell);

}

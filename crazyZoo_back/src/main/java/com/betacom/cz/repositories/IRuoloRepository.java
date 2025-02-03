package com.betacom.cz.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.cz.models.Ruolo;

public interface IRuoloRepository extends JpaRepository<Ruolo, Integer>{
	
	Optional<Ruolo> findByNome(String nome);

}

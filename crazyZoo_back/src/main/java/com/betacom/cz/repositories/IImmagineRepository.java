package com.betacom.cz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.betacom.cz.models.Immagine;

@Repository
public interface IImmagineRepository extends JpaRepository<Immagine, Integer>  {

}

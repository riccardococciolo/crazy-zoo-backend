package com.betacom.cz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.cz.models.Ordine;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer> {
	
    List<Ordine> findByUtente_Id(Integer id); // Corrected method

}

package com.betacom.cz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.cz.models.Ordine;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer> {

}

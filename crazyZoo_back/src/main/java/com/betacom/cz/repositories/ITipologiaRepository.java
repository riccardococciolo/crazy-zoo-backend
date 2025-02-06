package com.betacom.cz.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.cz.models.Tipologia;

public interface ITipologiaRepository extends JpaRepository<Tipologia, Integer> {

	Optional<Tipologia> findByNome(String nome);
}

package com.betacom.cz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.cz.models.Prodotto;

public interface IProdottoRepository extends JpaRepository<Prodotto, Integer> {

}

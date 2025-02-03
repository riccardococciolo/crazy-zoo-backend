package com.betacom.cz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.cz.models.Animale;

@Repository
public interface IAnimaleRepository extends JpaRepository<Animale, Integer>{

}

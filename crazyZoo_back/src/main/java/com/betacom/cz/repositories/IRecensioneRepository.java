package com.betacom.cz.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.cz.models.Recensione;

public interface IRecensioneRepository extends JpaRepository<Recensione, Integer> {
}

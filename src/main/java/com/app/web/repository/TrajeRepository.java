package com.app.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.web.entities.Talla;
import com.app.web.entities.Traje;

public interface TrajeRepository extends JpaRepository<Traje, Integer> {
    @Query("SELECT t FROM Talla t WHERE t.idTalla = :idTalla")
    Optional<Talla> findByIdTalla(int idTalla);
}

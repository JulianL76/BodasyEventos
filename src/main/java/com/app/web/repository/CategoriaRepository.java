package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.web.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}

package com.app.web.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.web.entities.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
	  @Query("SELECT p FROM Permiso p WHERE p.id IN :ids")
	    Set<Permiso> getPermisosByIds(@Param("ids") Set<Integer> ids);
}

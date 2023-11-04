package com.app.web.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.web.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {  
	@Query("SELECT r FROM Rol r JOIN r.permisos p WHERE p.id IN :permisos")
	Rol existeRolConPermisos(@Param("permisos") Set<Integer> permisos);

}

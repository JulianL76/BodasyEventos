package com.app.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.web.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByEmail(String email);
	public Optional<Usuario> findById(Integer id);
}

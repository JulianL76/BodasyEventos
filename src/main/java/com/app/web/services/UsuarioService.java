package com.app.web.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.app.web.entities.Usuario;

public interface UsuarioService extends UserDetailsService{
	public Usuario obtenerUsuarioPorId(Integer id);
	public Usuario guardar(Usuario usuario);
	public List<Usuario> listarUsuarios();
	public boolean existeUsuarioConEmail(String email);
	
}

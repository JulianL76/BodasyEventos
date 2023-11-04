package com.app.web.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.web.entities.Usuario;
import com.app.web.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
 
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Usuario guardar(Usuario usuarioRegistrar) {     
    	usuarioRegistrar.setPassword(passwordEncoder.encode(usuarioRegistrar.getPassword()));
        return usuarioRepository.save(usuarioRegistrar);
    }
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
        return new User(usuario.getEmail(), usuario.getPassword(), authorities);
    }
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	@Override
	public boolean existeUsuarioConEmail(String email) {
		Usuario usuarioExistente = usuarioRepository.findByEmail(email);
	    return usuarioExistente != null;
	}
}

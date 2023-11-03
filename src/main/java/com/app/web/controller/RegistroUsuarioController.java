package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.entities.Rol;
import com.app.web.entities.Usuario;
import com.app.web.repository.RolRepository;
import com.app.web.services.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolRepository rolRepository;

	public RegistroUsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public Usuario retornarNuevoUsuario() {
		return new Usuario();
	}

	@ModelAttribute("roles")
	public List<Rol> retornarRoles() {
	    return rolRepository.findAll(); 
	}
	
	@GetMapping()
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDePersona(@ModelAttribute("usuario") Usuario usuario) {
		usuarioService.guardar(usuario);
		return "redirect:/registro?exito";
    }
}

package com.app.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.web.entities.Permiso;
import com.app.web.entities.Usuario;
import com.app.web.repository.UsuarioRepository;
import com.app.web.repository.ClienteRepository;

@Controller
public class RegistroController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;

    @GetMapping("/")
    public String paginaPrincipal() {
        return "index";
    }

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/inicio")
    public String panelDeInicio(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username);
        if( usuario != null){
            Set<Permiso> permisos = usuario.getRol().getPermisos();
		    model.addAttribute("permisos", permisos);
        }
        else{
            if(clienteRepository.findByEmail(username) != null){
                return "paneldeinicio";
            }
        }
        return "paneldeinicio";
    }

    @GetMapping("/inactivo")
    public String inactivo() {
        return "inactivo";
    }

}
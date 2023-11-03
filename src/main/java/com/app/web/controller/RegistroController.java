package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.app.web.services.UsuarioService;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService servicio;
    
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    } 
   
}


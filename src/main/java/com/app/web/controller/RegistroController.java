package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @GetMapping("/BodasyEventos")
    public String paginaPrincipal() {
        return "index";
    }

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

}
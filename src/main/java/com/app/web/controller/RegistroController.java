package com.app.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {
    
    @GetMapping("/")
    public String paginaPrincipal() {
        return "index";
    } 
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    } 
    @GetMapping("/inicio")
    public String panelDeInicio() {
        return "paneldeinicio";
    } 
    @GetMapping("/inactivo")
    public String inactivo() {
        return "inactivo";
    } 
   
}


package com.app.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.entities.Permiso;

import com.app.web.entities.Usuario;
import com.app.web.repository.UsuarioRepository;
import com.app.web.services.ClienteService;
import com.app.web.entities.Cliente;

@Controller
public class RegistroController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private ClienteService clienteService;

    public RegistroController(ClienteService clienteService) {
        super();
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    public String paginaPrincipal() {
        return "index";
    }

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/carrito")
    public String carrito() {
        return "carrito";
    }

    @GetMapping("/registrarse")
    public String registrarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registrarUsuario";
    }

    @PostMapping("/registrarse")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        List<Cliente> clientes = clienteService.getAllClientes();
        for (Cliente clienteaux : clientes) {
            if (clienteaux.getCedula().equals(cliente.getCedula())) {
                return "redirect:/registrarse?error";
            }
        }
        clienteService.saveCliente(cliente);
        return "redirect:/registrarse?exito";
    }

    @GetMapping("/inicio")
    public String panelDeInicio(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username);
        // Cliente cliente = clienteRepository.findByCorreo(username);
        // System.out.println(cliente.getCorreo());
        Set<Permiso> permisos = usuario.getRol().getPermisos();
        model.addAttribute("permisos", permisos);
        return "paneldeinicio";
    }

    @GetMapping("/inactivo")
    public String inactivo() {
        return "inactivo";
    }

}
package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.web.entities.Cliente;
import com.app.web.services.ClienteService;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/inicio/cliente")
    public String listClientes(Model model) {
        List<Cliente> clientes = clienteService.getAllClientes();
        model.addAttribute("clientes", clientes);
        return "ListCliente";
    }

    @GetMapping("/inicio/cliente/nuevo")
    public String showForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "RegisterCliente";
    }

    @PostMapping("/inicio/cliente/nuevo")
    public String saveCliente(@ModelAttribute Cliente cliente) {
    	List<Cliente> clientes = clienteService.getAllClientes();
    	for (Cliente clienteaux: clientes) {
    		if(clienteaux.getCedula().equals(cliente.getCedula())) {
    			 return "redirect:/inicio/cliente/nuevo?error=Esa cedula ya se encuentra registrada en otro cliente ";
    		}
    	}
        clienteService.saveCliente(cliente);
        return "redirect:/inicio/cliente";
    }

    @GetMapping("/inicio/cliente/{cedula}/editar")
    public String showFormForUpdate(@PathVariable("cedula") Integer cedula, Model model) {
        Optional<Cliente> cliente = clienteService.getClienteById(cedula);
        model.addAttribute("cliente", cliente.orElse(new Cliente()));
        return "RegisterCliente";
    }
    @PostMapping("/inicio/cliente/editar/{cedula}")
    public String updateCliente(@ModelAttribute Cliente cliente) {
    	List<Cliente> clientes = clienteService.getAllClientes();
    	for (Cliente clienteaux: clientes) {
    		if(clienteaux.getCedula().equals(cliente.getCedula())) {
    			 return "redirect:/inicio/cliente/editar/{cedula}?error=Esa cedula ya se encuentra registrada en otro cliente ";
    		}
    	}
        clienteService.saveCliente(cliente);
        return "redirect:/clientes";
    }
    @GetMapping("/inicio/cliente/eliminar/{cedula}")
    public String deleteCliente(@PathVariable("cedula") Integer cedula) {
        clienteService.deleteCliente(cedula);
        return "redirect:/inicio/cliente";
    }
}

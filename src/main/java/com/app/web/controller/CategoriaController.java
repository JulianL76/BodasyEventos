package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.app.web.entities.Categoria;
import com.app.web.repository.CategoriaRepository;
@RequestMapping("/login")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/form")
    public String showForm(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "pruebaCategorias"; // Esto debería coincidir con el nombre de tu página HTML
    }
}


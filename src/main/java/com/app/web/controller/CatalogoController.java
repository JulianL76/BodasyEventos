package com.app.web.controller;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.web.entities.Categoria;
import com.app.web.entities.Traje;
import com.app.web.repository.TallaRepository;
import com.app.web.services.CategoriaService;
import com.app.web.services.TrajeService;

@Controller
public class CatalogoController {

    @Autowired
    private TrajeService trajeService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TallaRepository tallaRepository;

    @GetMapping("/catalogo")
    public String listarTrajes(Model model) {
        List<Categoria> categoriasNoVacias = categoriaService.obtenerTodasCategorias().stream()
                .filter(categoria -> !categoria.getTrajes().isEmpty())
                .collect(Collectors.toList());

        model.addAttribute("categorias", categoriasNoVacias);
        model.addAttribute("trajes", trajeService.obtenerTodosTrajes());
        return "catalogo";
    }

    @GetMapping("/catalogo/producto/{id}")
    public String mostrarProducto(@PathVariable Integer id, Model model) {
        Traje traje = trajeService.obtenerTrajePorId(id);
        model.addAttribute("traje", traje);
        model.addAttribute("tallas", tallaRepository.findAll());
        return "producto";
    }
}
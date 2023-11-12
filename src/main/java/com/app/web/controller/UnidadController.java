package com.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.web.entities.Talla;
import com.app.web.entities.Traje;
import com.app.web.entities.Unidad;

import com.app.web.repository.TallaRepository;
import com.app.web.repository.TrajeRepository;
import com.app.web.repository.UnidadRepository;
import com.app.web.services.UnidadService;

import org.springframework.ui.Model;

@Controller
public class UnidadController {
    private final UnidadService unidadService;
    private final UnidadRepository unidadRepository;
    private final TrajeRepository trajeRepository;
    private final TallaRepository tallaRepository;

    @Autowired
    public UnidadController(UnidadService unidadService, UnidadRepository unidadRepository,
            TrajeRepository trajeRepository, TallaRepository tallaRepository) {
        this.unidadService = unidadService;
        this.unidadRepository = unidadRepository;
        this.trajeRepository = trajeRepository;
        this.tallaRepository = tallaRepository;
    }
    // listar el inventario
    @GetMapping("/inicio/Inventario")
    public String listarUnidades(Model model) {
        List<Unidad> unidades = unidadRepository.findAll();
        model.addAttribute("unidades", unidades);

        return "ListUnidad";
    }

    @GetMapping("/inicio/Inventario/crear")
    public String mostrarFormCrearUnidad(Model model) {
        model.addAttribute("trajes", new ArrayList<>()); // Usar lista vacía en lugar de null
        List<Talla> tallas = tallaRepository.findAll();
        List<Traje> trajes = trajeRepository.findAll();
        model.addAttribute("tallas", tallas);
        model.addAttribute("trajes", trajes);
        return "RegisterUnidad";
    }

    @PostMapping("/inicio/Inventario/crear")
    public String createUnidad(@ModelAttribute("unidad") Unidad unidad) {
        unidadRepository.save(unidad);
        return "redirect:/inicio/Inventario";
    }

}
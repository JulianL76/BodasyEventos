package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import com.app.web.entities.Categoria;
import com.app.web.entities.Traje;
import com.app.web.repository.CategoriaRepository;
import com.app.web.services.TrajeService;

@Controller
public class TrajeController {
    private final CategoriaRepository categoriaRepository;
    @Autowired
    private TrajeService trajeService;
    
    public TrajeController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @GetMapping("/inicio/registroTraje")
    public String listarTrajes(Model model) {
        model.addAttribute("trajes", trajeService.obtenerTodosTrajes());
        return "listTraje";
    }

    @GetMapping("/inicio/registroTraje/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
    
        if (!categorias.isEmpty()) {
            model.addAttribute("categorias", categorias);
            model.addAttribute("traje", new Traje());
            return "RegisterTraje"; // Renderiza la vista si hay categorías
        } else {
            // Manejo de la situación en la que no hay categorías disponibles
            // Puedes redirigir a una página de error o realizar alguna otra acción.
            return "/inicio"; // Reemplaza "errorPage" con la vista de tu elección.
        }
    }

    @PostMapping("/inicio/registroTraje/nuevo")
    public String guardarTraje(@ModelAttribute Traje traje, @RequestParam("file") MultipartFile file) {
        // Procesar y guardar la imagen.
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                traje.setImagenes(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        trajeService.guardarTraje(traje);
        return "redirect:/inicio/registroTraje";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Traje traje = trajeService.obtenerTrajePorId(id);
        model.addAttribute("traje", traje);
        return "trajes/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarTraje(@PathVariable Integer id, @ModelAttribute Traje traje, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] imagenes = file.getBytes();
                traje.setImagenes(imagenes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        trajeService.actualizarTraje(id, traje);
        return "redirect:/trajes";
    }

    @GetMapping("/borrar/{id}")
    public String borrarTraje(@PathVariable Integer id) {
        trajeService.borrarTraje(id);
        return "redirect:/trajes";
    }
}

package com.app.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.app.web.entities.Talla;
import com.app.web.repository.TallaRepository;

@Controller
public class TallaController {
	@Autowired
	private  TallaRepository tallaRepository;

    public TallaController(TallaRepository tallaRepository) {
        this.tallaRepository = tallaRepository;
    }

	@GetMapping("/inicio/talla")
    public String showForm(Model model) {
        model.addAttribute("Tallas", tallaRepository.findAll());
        return "listTalla";
    }

    @GetMapping("/inicio/talla/nuevo")
    public String formularioNuevaTalla(Model model) {
        model.addAttribute("Talla", new Talla());
        model.addAttribute("esEdicion", false);
        return "formTalla";
    }

    @PostMapping("/inicio/talla/nuevo")
    public String guardarTalla(@ModelAttribute Talla talla) {
        tallaRepository.save(talla);
        return "redirect:/inicio/talla";
    }

    @GetMapping("/inicio/talla/{id}/editar")
    public String formularioEditarTalla(@PathVariable Integer id, Model model) {
        Talla Talla = tallaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría no válido"));
        model.addAttribute("Talla", Talla);
        model.addAttribute("esEdicion", true);
        return "formTalla";
    }
    @PostMapping("/inicio/talla/editar/{id}")
    public String editarTalla(@PathVariable Integer id, @ModelAttribute Talla Talla) {
        // Cargar el registro existente desde la base de datos
        Talla TallaExistente = tallaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría no válido"));

        // Aplicar los cambios al registro existente
        TallaExistente.setNombre(Talla.getNombre());

        // Guardar el registro actualizado
        tallaRepository.save(TallaExistente);

        return "redirect:/inicio/talla";
    }


    @GetMapping("/inicio/talla/{id}/borrar")
    public String borrarTalla(@PathVariable Integer id) {
        tallaRepository.deleteById(id);
        return "redirect:/inicio/talla";
    }
}


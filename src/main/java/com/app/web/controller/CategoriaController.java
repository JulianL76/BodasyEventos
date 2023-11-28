package com.app.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.app.web.entities.Categoria;
import com.app.web.repository.CategoriaRepository;

@Controller
public class CategoriaController {
	@Autowired
	private  CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

	@GetMapping("/inicio/categoria")
    public String showForm(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "listCategorias";
    }

    @GetMapping("/inicio/categoria/nuevo")
    public String formularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("esEdicion", false);
        return "formCategoria";
    }

    @PostMapping("/inicio/categoria/nuevo")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
    	List<Categoria> categorias = categoriaRepository.findAll();
    	for(Categoria cat : categorias) {
    	if(cat.getNombre().equalsIgnoreCase(categoria.getNombre())) {
    		 return "redirect:/inicio/categoria/nuevo?error=Ya existe una categoria con el nombre "+cat.getNombre();
    	}
    	}
        categoriaRepository.save(categoria);
        return "redirect:/inicio/categoria";
    }

    @GetMapping("/inicio/categoria/{id}/editar")
    public String formularioEditarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría no válido"));
        model.addAttribute("categoria", categoria);
        model.addAttribute("esEdicion", true);
        return "formCategoria";
    }
    @PostMapping("/inicio/categoria/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, @ModelAttribute Categoria categoria) {
        // Cargar el registro existente desde la base de datos
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría no válido"));
        List<Categoria> categorias = categoriaRepository.findAll();
    	for(Categoria cat : categorias) {
    	if(cat.getNombre().equalsIgnoreCase(categoria.getNombre())) {
    		 return "redirect:/inicio/categoria/nuevo?error=Ya existe una categoria con el nombre "+cat.getNombre();
     	}
    	}
        // Aplicar los cambios al registro existente
        categoriaExistente.setNombre(categoria.getNombre());

        // Guardar el registro actualizado
        categoriaRepository.save(categoriaExistente);

        return "redirect:/inicio/categoria";
    }


    @GetMapping("/inicio/categoria/{id}/borrar")
    public String borrarCategoria(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
        return "redirect:/inicio/categoria";
    }
}


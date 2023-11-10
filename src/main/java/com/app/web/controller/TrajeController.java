package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.apache.commons.codec.binary.Base64;

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

    // @PostMapping("/inicio/registroTraje/nuevo")
    // public String guardarTraje(@ModelAttribute Traje traje, @RequestParam("file")
    // MultipartFile file) {
    // // Verificar que la imagen se haya cargado correctamente y no esté vacía.
    // if (!file.isEmpty()) {
    // try {
    // byte[] bytes = file.getBytes();
    // // Convertir la imagen a formato Base64 utilizando Apache Commons Codec.
    // String base64Image = Base64.encodeBase64String(bytes);

    // // Validar la conversión a Base64 antes de guardarla en la entidad.
    // if (base64Image != null && !base64Image.isEmpty()) {
    // traje.setImagenes(base64Image.getBytes());
    // trajeService.guardarTraje(traje);
    // return "redirect:/inicio/registroTraje";
    // } else {
    // // Manejo de errores si la conversión no fue exitosa.
    // // Puedes redirigir a una página de error o realizar alguna otra acción.
    // return "errorPage"; // Reemplaza "errorPage" con la vista de error de tu
    // elección.
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // // Manejo de errores si hay un problema al obtener los bytes de la imagen.
    // // Puedes redirigir a una página de error o realizar alguna otra acción.
    // return "errorPage"; // Reemplaza "errorPage" con la vista de error de tu
    // elección.
    // }
    // } else {
    // // Manejo de errores si la imagen está vacía.
    // // Puedes redirigir a una página de error o realizar alguna otra acción.
    // return "errorPage"; // Reemplaza "errorPage" con la vista de error de tu
    // elección.
    // }
    // }

    public String guardarTraje(@ModelAttribute Traje traje, @RequestParam("file") MultipartFile file) {
        // Verificar que la imagen se haya cargado correctamente y no esté vacía.
        if (!file.isEmpty()) {
            try {
                if (!file.isEmpty()) {
                    byte[] bytes = file.getBytes();
                    traje.setImagenes(bytes);
                }
                trajeService.guardarTraje(traje);
                return "redirect:/inicio/registroTraje";
            } catch (IOException e) {
                e.printStackTrace();
                // Manejo de errores si hay un problema al obtener los bytes de la imagen.
                // Puedes redirigir a una página de error o realizar alguna otra acción.
                return "errorPage"; // Reemplaza "errorPage" con la vista de error de tu elección.
            }
        } else {
            // Manejo de errores si la imagen está vacía.
            // Puedes redirigir a una página de error o realizar alguna otra acción.
            return "errorPage"; // Reemplaza "errorPage" con la vista de error de tu elección.
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Traje traje = trajeService.obtenerTrajePorId(id);
        model.addAttribute("traje", traje);
        return "trajes/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarTraje(@PathVariable Integer id, @ModelAttribute Traje traje,
            @RequestParam("file") MultipartFile file) {
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

    @GetMapping("/inicio/registroTraje/{id}/borrar")
    public String borrarTraje(@PathVariable Integer id) {
        trajeService.borrarTraje(id);
        return "redirect:/inicio/registroTraje";
    }

    /*
     * @GetMapping("/inicio/registroTraje/ver-imagen/{idTraje}")
     * public String verImagen(@PathVariable Integer idTraje, Model model) {
     * Traje traje = trajeService.obtenerTrajePorId(idTraje);
     * if (traje != null && traje.getImagenes() != null) {
     * // Convierte el arreglo de bytes en una representación en Base64
     * String base64Image = Base64.getEncoder().encodeToString(traje.getImagenes());
     * 
     * // Agrega la imagen a la vista
     * model.addAttribute("imagenBase64", base64Image);
     * 
     * return "verImagen"; // Crea una nueva plantilla para mostrar la imagen
     * } else {
     * // Manejo de errores si el traje o la imagen no existen
     * return "error"; // Crea una vista de error
     * }
     * }
     */
}

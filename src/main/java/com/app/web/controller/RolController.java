package com.app.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.web.entities.Permiso;
import com.app.web.entities.Rol;
import com.app.web.entities.Usuario;
import com.app.web.repository.PermisoRepository;
import com.app.web.repository.RolRepository;
import com.app.web.repository.UsuarioRepository;
import com.app.web.services.UsuarioService;

@Controller
public class RolController {
	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PermisoRepository permisoRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/inicio/configuracion")
	public String panelDeConfig(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		List<Permiso> permisos = permisoRepository.findAll();
		model.addAttribute("permisos", permisos);
		List<Rol> roles = rolRepository.findAll();
		model.addAttribute("roles", roles);
		List<Usuario> usuarios = usuarioService.listarUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "configuracion";
	}

	@GetMapping("/inicio/configuracion")
	public String panelDeConfig(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		List<Permiso> permisos = permisoRepository.findAll();
		model.addAttribute("permisos", permisos);
		List<Rol> roles = rolRepository.findAll();
		model.addAttribute("roles", roles);
		return "configuracion";
	}

	@PostMapping("/inicio/configuracion")
	public String guardarRol(@RequestParam Map<String, String> params, @RequestParam String nombre, Model model) {
		Set<Integer> permisosActivados = params.keySet().stream()
				.filter(key -> key.startsWith("permiso_"))
				.map(key -> Integer.parseInt(key.replace("permiso_", "")))
				.collect(Collectors.toSet());
		List<Rol> rolesExistentes = rolRepository.findAll();
		// Variable para almacenar el rol existente con los mismos permisos
		Rol rolExistenteConPermisos = null;
		// Verificar si ya existe un rol con los mismos permisos
		for (Rol rol : rolesExistentes) {
			if (rol.getPermisos().stream()
					.map(Permiso::getId)
					.collect(Collectors.toSet())
					.equals(permisosActivados)) {
				rolExistenteConPermisos = rol;
				break; // Rompe el bucle una vez que se encuentra un rol con los mismos permisos
			}
		}
		if (rolExistenteConPermisos != null) {
			model.addAttribute("error", "El rol " + rolExistenteConPermisos.getNombre() + " ya tiene estos permisos.");
			List<Permiso> permisos = permisoRepository.findAll();
			model.addAttribute("permisos", permisos);
			return "configuracion";
		}
		// Si no existe un rol con los mismos permisos, crea un nuevo rol
		Rol rol = new Rol();
		rol.setNombre(nombre);
		Set<Permiso> permisosNuevos = permisoRepository.getPermisosByIds(permisosActivados);
		rol.setPermisos(permisosNuevos);
		rolRepository.save(rol);
		return "redirect:/inicio/configuracion";
	}

	@GetMapping("/usuarios/{id}/toggle-activo")
	public String toggleActivo(@PathVariable Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		Usuario obtenerUsuario = usuario.get();
		if (obtenerUsuario != null) {
			obtenerUsuario.setActivo(! obtenerUsuario.isActivo());
			usuarioService.guardar(obtenerUsuario);
		}
		return "redirect:/inicio/configuracion";
	}
}
}


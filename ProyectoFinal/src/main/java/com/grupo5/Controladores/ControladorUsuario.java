package com.grupo5.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.grupo5.Servicios.ServiciosUsuario;
import com.grupo5.modelos.Usuario;

@Controller
public class ControladorUsuario {

	@Autowired
	private ServiciosUsuario servicioUsuario;
	
	// Mostrar el formulario de registro
	@GetMapping("/registro")
	public String mostrarFormularioRegistro(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	//Procesar el registro
	@PostMapping("/procesaRegistro")
	public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model modelo) {
		// Verifica si el campo password está vacío antes de guardar
	    if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        modelo.addAttribute("error", "La contraseña no puede estar vacía");
	        return "registro";
	    }
		Usuario registrado = servicioUsuario.registrarUsuario(usuario);
		if(registrado == null) {
			modelo.addAttribute("error", "El correo o nombre de usuario ya existe");
			return "registro";
		}
		return "redirect:/login";
	}
}

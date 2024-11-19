package com.grupo5.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.grupo5.Servicios.ServicioSueldo;
import com.grupo5.Servicios.ServiciosUsuario;
import com.grupo5.modelos.Sueldo;
import com.grupo5.modelos.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorUsuario {

	@Autowired
	private ServiciosUsuario servicioUsuario;
	@Autowired
    private ServicioSueldo servicioSueldo;
	
	// Mostrar la vista de inicio después del login
    @GetMapping("/inicio")
    public String mostrarInicio(HttpSession session, Model modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        // Obtener el último sueldo ingresado por el usuario
        Sueldo sueldo = servicioSueldo.obtenerUltimoSueldoPorUsuario(usuario);
        modelo.addAttribute("sueldo", sueldo);

        return "inicio";
    }
	
	// Mostrar el formulario de registro
	@GetMapping("/registro")
	public String mostrarFormularioRegistro(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	//Procesar el registro
	@PostMapping("/procesaRegistro")
	public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model modelo) {
		// Verifica si el campo password está vacío antes de guardar
		System.out.println("hola");
	    if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        modelo.addAttribute("error", "La contraseña no puede estar vacía");
	        return "login";
	    }
		Usuario registrado = servicioUsuario.registrarUsuario(usuario);
		if(registrado == null) {
			modelo.addAttribute("error", "El correo o nombre de usuario ya existe");
			return "login";
		}
		
		return "redirect:/";
	}

	 // Mostrar el formulario de login
    @GetMapping("/")
    public String mostrarFormularioLogin(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "login";
    }

    // Procesar el login
    @PostMapping("/procesaLogin")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario, Model modelo, HttpSession session) {
        // Buscar el usuario por correo
        Usuario usuarioExistente = servicioUsuario.obtenerUsuarioPorCorreo(usuario.getCorreo());

        // Validar credenciales
        if (usuarioExistente != null && usuarioExistente.getPassword().equals(usuario.getPassword())) {
            // Establecer el usuario en la sesión
            session.setAttribute("usuarioLogueado", usuarioExistente);
            return "redirect:/inicio"; // Redirigir a la página de inicio
        } else {
            modelo.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }
}

package com.grupo5.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.grupo5.Servicios.ServicioSueldo;
import com.grupo5.modelos.Sueldo;
import com.grupo5.modelos.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorSueldo {

	@Autowired
	private ServicioSueldo servicioSueldo;
	

    // Procesar el registro de un sueldo
    @PostMapping("/sueldo")
    public String procesarRegistroSueldo(@ModelAttribute("sueldo") Sueldo sueldo, HttpSession session, Model modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        // Asociar el sueldo con el usuario autenticado
        sueldo.setUsuario(usuario);
        servicioSueldo.registrarSueldo(sueldo);

        return "redirect:/inicio"; // Redirige a la vista de inicio después de registrar el sueldo
    }
}

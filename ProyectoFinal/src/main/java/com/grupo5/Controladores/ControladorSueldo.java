package com.grupo5.Controladores;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo5.Servicios.ServicioSueldo;
import com.grupo5.modelos.Sueldo;
import com.grupo5.modelos.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorSueldo {

	@Autowired
	private ServicioSueldo servicioSueldo;

	@PostMapping("/sueldo")
    public String procesarRegistroOSumaSueldo(@RequestParam("monto") BigDecimal monto, HttpSession session, Model modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        // Registrar o sumar el monto al sueldo existente
        servicioSueldo.registrarOSumarSueldo(usuario, monto);

        // Redirigir a la vista de inicio
        return "redirect:/inicio";
    }
}

package com.grupo5.Controladores;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        BigDecimal sueldoAcumulado = servicioSueldo.obtenerSueldoAcumulado(usuario);
        modelo.addAttribute("sueldoAcumulado", sueldoAcumulado);

        return "inicio"; // Retorna la vista "inicio.jsp"
    }

    // Mostrar la vista de login/registro
    @GetMapping("/")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("mostrarRegistro", false); // Por defecto, muestra el login
        return "login";
    }

    // Procesar el registro
    @PostMapping("/procesaRegistro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model modelo) {
        String error = validarRegistro(usuario);
        if (error != null) {
            modelo.addAttribute("errorRegistro", error);
            modelo.addAttribute("mostrarRegistro", true);
            return "login";
        }

        Usuario registrado = servicioUsuario.registrarUsuario(usuario);
        if (registrado == null) {
            modelo.addAttribute("errorRegistro", "El correo o el nombre de usuario ya están registrados");
            modelo.addAttribute("mostrarRegistro", true);
            return "login";
        }

        return "redirect:/"; // Redirigir al login tras un registro exitoso
    }

    // Procesar el login
    @PostMapping("/procesaLogin")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario, Model modelo, HttpSession session) {
        Usuario usuarioLogueado = servicioUsuario.obtenerUsuarioPorCorreo(usuario.getCorreo());
        if (usuarioLogueado != null && usuarioLogueado.getPassword().equals(usuario.getPassword())) {
            session.setAttribute("usuarioLogueado", usuarioLogueado);
            return "redirect:/inicio"; // Redirigir al inicio
        }

        modelo.addAttribute("errorLogin", "Correo o contraseña incorrectos");
        modelo.addAttribute("mostrarRegistro", false);
        return "login";
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Método de validación de registro
    private String validarRegistro(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            return "El nombre no puede estar vacío";
        }
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()) {
            return "El nombre de usuario no puede estar vacío";
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            return "El correo no puede estar vacío";
        }
        if (!usuario.getCorreo().contains("@")) {
            return "El correo debe ser válido";
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            return "La contraseña no puede estar vacía";
        }
        if (usuario.getPassword().length() < 8) {
            return "La contraseña debe tener al menos 8 caracteres";
        }
        return null; // Sin errores
    }
}


package com.grupo5.Controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.grupo5.Repositorios.RepositorioUsuario;
import com.grupo5.modelos.Usuario;

@Controller
public class controladorLogin {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Mostrar el formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "login"; // Nombre del archivo que tenga las vistas
    }

    // Procesar el login
    @PostMapping("/procesarLogin")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario, Model modelo) {
        Optional<Usuario> usuarioExistente = repositorioUsuario.findByCorreo(usuario.getCorreo());

        if (usuarioExistente != null && usuarioExistente.getPassword().equals(usuario.getPassword())) {
            // Credenciales correctas
            return "redirect:/inicio"; // Redirige al inicio
        } else {
            // Credenciales incorrectas
            modelo.addAttribute("error", "Correo o contraseña incorrectos.");
            return "login"; // Muestra de nuevo el formulario de login con el mensaje de error
        }
    }
}

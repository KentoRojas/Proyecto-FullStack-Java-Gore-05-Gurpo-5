package com.grupo5.Controladores;

import com.grupo5.Repositorios.RepositorioUsuario;
import com.grupo5.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class controladorLogin {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login.jsp"; // puse login pero puede ser inicio o algun otro, es de referencia
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("nombreUsuario") String nombreUsuario,
                                 @RequestParam("password") String password,
                                 Model modelo) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(nombreUsuario).orElse(null);

        if (usuario != null && usuario.getPassword().equals(password)) {
            return "redirect:/inicio"; // Página principal después del login
        } else {
            modelo.addAttribute("error", "Credenciales incorrectas");
            return "login.jsp"; // Volver a la página de login con un mensaje de error
        }
    }
}

package com.grupo5.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grupo5.Repositorios.RepositorioGasto;
import com.grupo5.modelos.Gasto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Controller
public class ControladorGasto {

    @Autowired
    private RepositorioGasto repositorioGasto;

    @GetMapping("/formularioGasto")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("gasto", new Gasto());
        return "vistaGasto"; // Nombre del jsp
    }

    @PostMapping("/formularioGasto")
    public String guardarRegistro(@RequestParam("descrpicion") String descripcion,
                                  @RequestParam("monto") BigDecimal monto,
                                  @RequestParam("fecha") Date fecha,
                                  @RequestParam("foto") MultipartFile foto) {
    	Gasto gasto = new Gasto();
        gasto.setDescripcion(descripcion);
        gasto.setMonto(monto);
        gasto.setFecha(fecha);

        // Guardar la imagen si se ha subido
        if (!foto.isEmpty()) {
            try {
                gasto.setBoleta(foto.getBytes()); // Guardar el contenido de la imagen en el objeto
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        repositorioGasto.save(gasto); // Guardar en la base de datos
        return "redirect:/gasto"; // Redirigir después de guardar
    }
}


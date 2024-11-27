package com.grupo5.Controladores;

import com.grupo5.Servicios.ServicioCategoria;
import com.grupo5.Servicios.ServicioGasto;
import com.grupo5.Servicios.ServicioGrupo;
import com.grupo5.Servicios.ServiciosUsuario;
import com.grupo5.modelos.Categoria;
import com.grupo5.modelos.Gasto;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/grupos")
public class ControladorGrupo {

    @Autowired
    private ServicioGrupo servicioGrupo;

    @Autowired
    private ServicioGasto servicioGasto;

    @Autowired
    private ServiciosUsuario servicioUsuario;

    @Autowired
    private ServicioCategoria servicioCategoria;

    // Mostrar la página de gestión de grupos
    @GetMapping
    public String mostrarGrupos(Model modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        List<Grupo> grupos = servicioGrupo.obtenerGrupos();
        modelo.addAttribute("grupo", grupos);

        return "grupo"; // Muestra la vista principal de gestión de grupos
    }

    // Crear un nuevo grupo
    @PostMapping("/crear")
    public String crearGrupo(@ModelAttribute Grupo grupo, HttpSession session, Model modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        // Validación manual
        if (grupo.getNombreGrupo() == null || grupo.getNombreGrupo().isEmpty()) {
            modelo.addAttribute("error", "El nombre del grupo no puede estar vacío.");
            return "grupo";
        }

        if (grupo.getPresupuestoTotal() == null || grupo.getPresupuestoTotal() <= 0) {
            modelo.addAttribute("error", "El presupuesto total debe ser mayor que cero.");
            return "grupo";
        }

        grupo = servicioGrupo.crearGrupo(grupo, usuario);
        return "redirect:/grupos";
    }

    // Ver detalles del grupo
    @GetMapping("/detalle/{idGrupo}")
    public String verDetalleGrupo(@PathVariable Long idGrupo, Model modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        Grupo grupo = servicioGrupo.obtenerGrupoPorId(idGrupo);

        if (grupo == null) {
            modelo.addAttribute("error", "El grupo no existe o no tienes acceso.");
            return "redirect:/grupos";
        }

        try {
            List<Gasto> historial = servicioGasto.obtenerGastosPorGrupo(idGrupo);
            List<Categoria> categorias = servicioCategoria.obtenerTodasLasCategorias();

            modelo.addAttribute("grupo", grupo);
            modelo.addAttribute("historial", historial);
            modelo.addAttribute("categorias", categorias);

            return "detalleGrupo";
        } catch (Exception e) {
            modelo.addAttribute("error", "Ocurrió un error al cargar el detalle del grupo.");
            return "redirect:/grupos";
        }
    }

    // Añadir presupuesto al grupo
    @PostMapping("/{idGrupo}/añadirPresupuesto")
    public String añadirPresupuesto(@PathVariable Long idGrupo,
                                    @RequestParam Integer montoPresupuesto,
                                    HttpSession session,
                                    Model modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/";
        }

        if (montoPresupuesto == null || montoPresupuesto <= 0) {
            modelo.addAttribute("error", "El monto debe ser mayor que cero.");
            return "redirect:/grupos/detalle/" + idGrupo;
        }

        boolean resultado = servicioGrupo.agregarPresupuesto(idGrupo, montoPresupuesto);

        if (!resultado) {
            modelo.addAttribute("error", "No se pudo añadir el presupuesto.");
        } else {
            modelo.addAttribute("exito", "Presupuesto añadido correctamente.");
        }

        return "redirect:/grupos/detalle/" + idGrupo;
    }

    // Registrar un gasto
    @PostMapping("/{idGrupo}/registrarGasto")
    public String registrarGasto(
        @PathVariable Long idGrupo,
        @RequestParam("montoGasto") Integer montoGasto,
        @RequestParam("descripcionGasto") String descripcionGasto,
        @RequestParam("categoriaGasto") Long idCategoria,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        Grupo grupo = servicioGrupo.obtenerGrupoPorId(idGrupo);

        if (grupo == null) {
            redirectAttributes.addFlashAttribute("error", "El grupo no existe.");
            return "redirect:/grupos";
        }

        if (grupo.getPresupuestoRestante() < montoGasto) {
            redirectAttributes.addFlashAttribute("error", "El monto excede el presupuesto disponible.");
            return "redirect:/grupos/detalle/" + idGrupo;
        }

        Gasto gasto = new Gasto();
        gasto.setMonto(montoGasto);
        gasto.setDescripcion(descripcionGasto); // Asignar descripción
        gasto.setFecha(LocalDate.now());
        gasto.setGrupo(grupo);
        gasto.setTipo(servicioCategoria.obtenerCategoriaPorID(idCategoria)); // Asegúrate de que el método exista
        gasto.setUsuario(usuario);

        boolean exito = servicioGasto.registrarGasto(gasto);
        if (!exito) {
            redirectAttributes.addFlashAttribute("error", "No se pudo registrar el gasto.");
        } else {
            redirectAttributes.addFlashAttribute("exito", "Gasto registrado correctamente.");
        }

        return "redirect:/grupos/detalle/" + idGrupo;
    }
}

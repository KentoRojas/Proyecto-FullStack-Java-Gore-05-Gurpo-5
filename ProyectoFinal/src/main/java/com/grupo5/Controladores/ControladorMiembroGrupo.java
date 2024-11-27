package com.grupo5.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.grupo5.Servicios.ServicioMiembroGrupo;
import com.grupo5.modelos.MiembroGrupo;
import com.grupo5.modelos.MiembroGrupoId;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Usuario;

import java.util.List;

@Controller
@RequestMapping("/miembro-grupo")
public class ControladorMiembroGrupo {

    @Autowired
    private ServicioMiembroGrupo servicioMiembroGrupo;

    // Obtener miembro de un grupo
    @GetMapping("/{idUsuario}/{idGrupo}")
    public String obtenerMiembroGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo, Model modelo) {
        MiembroGrupoId id = new MiembroGrupoId(idUsuario, idGrupo);
        MiembroGrupo miembroGrupo = servicioMiembroGrupo.obtenerMiembroPorId(id);

        if (miembroGrupo != null) {
            modelo.addAttribute("miembroGrupo", miembroGrupo);
            return "detalleMiembroGrupo"; // Vista que muestra detalles del miembro
        } else {
            modelo.addAttribute("error", "El miembro no existe en el grupo.");
            return "error"; // Vista de error
        }
    }

    // Registrar un nuevo miembro en un grupo
    @PostMapping("/registrar")
    public String registrarMiembroGrupo(@ModelAttribute MiembroGrupo miembroGrupo, Model modelo) {
        try {
            MiembroGrupo nuevoMiembro = servicioMiembroGrupo.registrarMiembroGrupo(miembroGrupo);
            modelo.addAttribute("miembroGrupo", nuevoMiembro);
            return "redirect:/miembro-grupo/lista"; // Redirigir a la lista de miembros
        } catch (Exception e) {
            modelo.addAttribute("error", "Error al registrar el miembro: " + e.getMessage());
            return "error"; // Vista de error
        }
    }

    // Eliminar un miembro de un grupo
    @PostMapping("/eliminar/{idUsuario}/{idGrupo}")
    public String eliminarMiembroGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo, Model modelo) {
        boolean eliminado = servicioMiembroGrupo.eliminarMiembro(idUsuario, idGrupo);

        if (eliminado) {
            modelo.addAttribute("mensaje", "Miembro eliminado con éxito.");
        } else {
            modelo.addAttribute("error", "No se pudo eliminar el miembro.");
        }
        return "redirect:/miembro-grupo/lista"; // Redirigir a la lista de miembros
    }

    // Actualizar el rol de un miembro
    @PostMapping("/actualizar/{idUsuario}/{idGrupo}")
    public String actualizarRolDeMiembro(@PathVariable Long idUsuario, @PathVariable Long idGrupo,
                                         @RequestParam String nuevoRol, Model modelo) {
        boolean actualizado = servicioMiembroGrupo.cambiarRolDeMiembro(idUsuario, idGrupo, nuevoRol);

        if (actualizado) {
            modelo.addAttribute("mensaje", "Rol actualizado con éxito.");
            return "redirect:/miembro-grupo/lista"; // Redirigir a la lista de miembros
        } else {
            modelo.addAttribute("error", "No se pudo actualizar el rol.");
            return "error"; // Vista de error
        }
    }

    // Listar todos los miembros del grupo
    @GetMapping("/lista/{idGrupo}")
    public String listarMiembrosGrupo(@PathVariable Long idGrupo, Model modelo) {
        List<MiembroGrupo> miembros = servicioMiembroGrupo.listarMiembrosPorGrupo(idGrupo);
        modelo.addAttribute("miembros", miembros);
        return "listaMiembrosGrupo"; // Vista que muestra la lista de miembros
    }
}

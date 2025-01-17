package com.grupo5.Controladores;

import com.grupo5.Servicios.ServicioGasto;
import com.grupo5.Servicios.ServicioGrupo;
import com.grupo5.modelos.Categoria;
import com.grupo5.modelos.Gasto;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/gastos")
public class ControladorGasto {

    @Autowired
    private ServicioGasto servicioGasto;

    @Autowired
    private ServicioGrupo servicioGrupo;

    // Registrar un gasto en un grupo
    @PostMapping("/{idGrupo}/registrar")
    public String registrarGasto(@PathVariable Long idGrupo,
                                  @RequestParam Integer monto,
                                  @RequestParam String descripcion,
                                  HttpSession session,
                                  Model modelo) {
        // Validar usuario en sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        // Validar monto
        if (monto == null || monto <= 0) {
            modelo.addAttribute("error", "El monto debe ser mayor a cero.");
            return "redirect:/grupos/detalle/" + idGrupo; // Devuelve a la vista del grupo
        }

        // Obtener el grupo al que pertenece el gasto
        Grupo grupo = servicioGrupo.obtenerGrupoPorId(idGrupo);
        if (grupo == null) {
            modelo.addAttribute("error", "El grupo no existe.");
            return "redirect:/grupos"; // Redirige a la lista de grupos
        }

        // Verificar si el presupuesto es suficiente
        if (grupo.getPresupuestoTotal() < monto) {
            modelo.addAttribute("error", "El presupuesto del grupo no es suficiente para este gasto.");
            return "redirect:/grupos/detalle/" + idGrupo; // Redirige al detalle del grupo
        }

        // Crear y registrar el gasto
        Gasto gasto = new Gasto();
        gasto.setMonto(monto);
        gasto.setDescripcion(descripcion);
        gasto.setFecha(LocalDate.now());
        gasto.setUsuario(usuario);
        gasto.setGrupo(grupo);

        boolean registrado = servicioGasto.registrarGasto(gasto);
        if (!registrado) {
            modelo.addAttribute("error", "No se pudo registrar el gasto.");
        } else {
            modelo.addAttribute("exito", "Gasto registrado correctamente.");
        }

        return "redirect:/grupos/detalle/" + idGrupo; // Redirige al detalle del grupo
    }

    // Listar gastos de un grupo
    @GetMapping("/{idGrupo}/listar")
    public String listarGastosPorGrupo(@PathVariable Long idGrupo, HttpSession session, Model modelo) {
        // Validar usuario en sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/"; // Redirige al login si no hay usuario en sesión
        }

        // Verificar existencia del grupo
        Grupo grupo = servicioGrupo.obtenerGrupoPorId(idGrupo);
        if (grupo == null) {
            modelo.addAttribute("error", "El grupo no existe.");
            return "redirect:/grupos"; // Redirige a la lista de grupos
        }

        try {
            // Obtener gastos del grupo
            List<Gasto> gastos = servicioGasto.obtenerGastosPorGrupo(idGrupo);

            // Pasar datos al modelo
            modelo.addAttribute("gastos", gastos);
            modelo.addAttribute("grupo", grupo);

            return "detalleGrupo"; // Muestra la vista del detalle del grupo con los gastos
        } catch (Exception e) {
            modelo.addAttribute("error", "Ocurrió un error al listar los gastos.");
            return "detalleGrupo"; // Devuelve a la misma vista con el mensaje de error
        }
    }
    
	// Obtener la ruta desde el archivo de configuración
    @Value("${gasto.imagenes.ruta}")
    private String uploadDir;

    @PostConstruct // Ejecuta este método al iniciar la aplicación
    public void crearDirectorio() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Crea la carpeta si no existe
            System.out.println("Directorio creado en: " + uploadDir);
        }
    }
    private static final String UPLOAD_DIR = "C:\\src\\main\\resources\\static\\boletas";

    @GetMapping
    public List<Gasto> obtenerGastos() {
        return servicioGasto.obtenerTodos();
    }

    @PostMapping
    public Gasto crearGasto(@RequestParam("descripcion") String descripcion,
                            @RequestParam("categoria") Categoria categoria,
                            @RequestParam("monto") int monto,
                            @RequestParam("fecha") String fecha,
                            @RequestParam("imagen") MultipartFile imagen) throws IOException {

        // Guardar imagen en sistema de archivos
        String imagenPath = UPLOAD_DIR + "/" + imagen.getOriginalFilename();
        File destino = new File(imagenPath);
        imagen.transferTo(destino);

        // Crear y guardar el gasto
        Gasto gasto = new Gasto();
        gasto.setDescripcion(descripcion);
        gasto.setTipo(categoria);
        gasto.setMonto(monto);
        gasto.setFecha(LocalDate.parse(fecha));
        gasto.setBoleta(imagenPath);

        return servicioGasto.guardarGasto(gasto);
    }
}

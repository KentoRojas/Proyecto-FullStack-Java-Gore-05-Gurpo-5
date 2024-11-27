package com.grupo5.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioGasto;
import com.grupo5.Repositorios.RepositorioGrupo;
import com.grupo5.modelos.Categoria;
import com.grupo5.modelos.Gasto;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Usuario;

@Service
public class ServicioGasto {

    @Autowired
    private RepositorioGasto repositorioGasto;

    @Autowired
    private ServicioGrupo servicioGrupo;

    @Autowired
    private RepositorioGrupo repositorioGrupo;

    // Método para registrar un nuevo gasto
    public boolean registrarGasto(Gasto gasto) {
        // Validar parámetros
        if (gasto == null || gasto.getGrupo() == null || gasto.getMonto() == null || gasto.getMonto() <= 0) {
            throw new IllegalArgumentException("Los datos del gasto son inválidos.");
        }

        Grupo grupo = servicioGrupo.obtenerGrupoPorId(gasto.getGrupo().getIdGrupo());
        if (grupo == null) {
            return false; // No se encontró el grupo
        }

        // Verificar si el presupuesto es suficiente
        if (grupo.getPresupuestoTotal() < gasto.getMonto()) {
            return false; // No se permite registrar si excede el presupuesto
        }

        // Restar el gasto del presupuesto total
        grupo.setPresupuestoTotal(grupo.getPresupuestoTotal() - gasto.getMonto());
        repositorioGrupo.save(grupo); // Guardar cambios en el grupo

        // Guardar el gasto
        repositorioGasto.save(gasto);
        return true; // Gasto registrado con éxito
    }

    // Método para registrar un gasto en un grupo
    public Gasto registrarGastoEnGrupo(Gasto gasto, Grupo grupo, Usuario usuario) {
        if (gasto == null || grupo == null || usuario == null || gasto.getMonto() == null || gasto.getMonto() <= 0) {
            throw new IllegalArgumentException("Los datos del gasto, grupo o usuario son inválidos.");
        }

        if (gasto.getFecha() == null) {
            gasto.setFecha(LocalDate.now()); // Asignar fecha actual si no se proporciona
        }

        gasto.setGrupo(grupo);
        gasto.setUsuario(usuario);

        return repositorioGasto.save(gasto);
    }

    // Obtener un gasto por su ID
    public Gasto obtenerGastoPorId(Long id) {
        return repositorioGasto.findById(id).orElse(null);
    }

    // Obtener todos los gastos de un usuario
    public List<Gasto> obtenerGastosPorUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        return repositorioGasto.findByUsuario(usuario);
    }

    // Obtener gastos por categoría
    public List<Gasto> obtenerGastosPorCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        }
        return repositorioGasto.findByTipo(categoria);
    }

    // Obtener gastos en un rango de fechas
    public List<Gasto> obtenerGastosPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("Las fechas son inválidas. Asegúrate de que fechaInicio sea anterior a fechaFin.");
        }
        return repositorioGasto.findByFechaBetween(fechaInicio, fechaFin);
    }

    // Obtener los gastos de un usuario en un rango de fechas
    public List<Gasto> obtenerGastosPorUsuarioYFecha(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("Las fechas son inválidas. Asegúrate de que fechaInicio sea anterior a fechaFin.");
        }
        return repositorioGasto.findByUsuarioAndFechaBetween(usuario, fechaInicio, fechaFin);
    }

    // Obtener los gastos de un grupo por su ID
    public List<Gasto> obtenerGastosPorGrupo(Long idGrupo) {
        if (idGrupo == null) {
            throw new IllegalArgumentException("El ID del grupo no puede ser nulo.");
        }
        return repositorioGasto.findByGrupo_IdGrupo(idGrupo);
    }

    // Eliminar un gasto por su ID
    public boolean eliminarGasto(Long id) {
        if (id == null || !repositorioGasto.existsById(id)) {
            return false; // Retorna false si el ID es inválido o no existe
        }
        repositorioGasto.deleteById(id);
        return true;
    }

    // Actualizar un gasto existente
    public Gasto actualizarGasto(Long id, Gasto gastoActualizado) {
        if (id == null || gastoActualizado == null) {
            throw new IllegalArgumentException("El ID o los datos del gasto son inválidos.");
        }

        Gasto gastoExistente = obtenerGastoPorId(id);
        if (gastoExistente != null) {
            gastoExistente.setMonto(gastoActualizado.getMonto());
            gastoExistente.setFecha(gastoActualizado.getFecha());
            gastoExistente.setTipo(gastoActualizado.getTipo());
            gastoExistente.setBoleta(gastoActualizado.getBoleta());
            gastoExistente.setDescripcion(gastoActualizado.getDescripcion());
            return repositorioGasto.save(gastoExistente);
        }
        return null; // Retorna null si el gasto no existe
    }
}
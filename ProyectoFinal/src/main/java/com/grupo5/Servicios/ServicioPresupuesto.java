package com.grupo5.Servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioPresupuesto;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Presupuesto;
import com.grupo5.modelos.Usuario;

@Service
public class ServicioPresupuesto {

	@Autowired
	private RepositorioPresupuesto repositorioPresupuesto;
	
	 // Método para registrar un nuevo presupuesto
    public Presupuesto registrarPresupuesto(Presupuesto presupuesto) {
        if (presupuesto != null) {
            return repositorioPresupuesto.save(presupuesto);
        }
        return null; // Retorna null si el presupuesto es nulo
    }

    // Método para obtener un presupuesto por su ID
    public Presupuesto obtenerPresupuestoPorId(Long id) {
        Optional<Presupuesto> presupuesto = repositorioPresupuesto.findById(id);
        return presupuesto.orElse(null);
    }

    // Método para obtener todos los presupuestos de un usuario
    public List<Presupuesto> obtenerPresupuestosPorUsuario(Usuario usuario) {
        return repositorioPresupuesto.findByUsuario(usuario);
    }

    // Método para obtener todos los presupuestos de un grupo
    public List<Presupuesto> obtenerPresupuestosPorGrupo(Grupo grupo) {
        return repositorioPresupuesto.findByGrupo(grupo);
    }

    // Método para obtener presupuestos por usuario y grupo
    public List<Presupuesto> obtenerPresupuestosPorUsuarioYGrupo(Usuario usuario, Grupo grupo) {
        return repositorioPresupuesto.findByUsuarioAndGrupo(usuario, grupo);
    }

    // Método para obtener presupuestos por un rango de fechas
    public List<Presupuesto> obtenerPresupuestosPorFecha(Date fechaInicio, Date fechaFin) {
        return repositorioPresupuesto.findByFechaInicioBetween(fechaInicio, fechaFin);
    }

    // Método para eliminar un presupuesto por su ID
    public boolean eliminarPresupuesto(Long id) {
        if (repositorioPresupuesto.existsById(id)) {
        	repositorioPresupuesto.deleteById(id);
            return true;
        }
        return false; // Retorna false si el presupuesto no existe
    }

    // Método para actualizar un presupuesto existente
    public Presupuesto actualizarPresupuesto(Long id, Presupuesto presupuestoActualizado) {
        Presupuesto presupuestoExistente = obtenerPresupuestoPorId(id);
        if (presupuestoExistente != null) {
            presupuestoExistente.setMontoMaximo(presupuestoActualizado.getMontoMaximo());
            presupuestoExistente.setFechaInicio(presupuestoActualizado.getFechaInicio());
            presupuestoExistente.setFechaFin(presupuestoActualizado.getFechaFin());
            presupuestoExistente.setUsuario(presupuestoActualizado.getUsuario());
            presupuestoExistente.setGrupo(presupuestoActualizado.getGrupo());
            return repositorioPresupuesto.save(presupuestoExistente);
        }
        return null; // Retorna null si el presupuesto no existe
    }
}

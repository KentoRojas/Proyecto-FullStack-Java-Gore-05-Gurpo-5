package com.grupo5.Repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Presupuesto;
import com.grupo5.modelos.Usuario;

@Repository
public interface RepositorioPresupuesto extends CrudRepository<Presupuesto, Long> {

	// Buscar todos los presupuestos de un usuario
    List<Presupuesto> findByUsuario(Usuario usuario);

    // Buscar todos los presupuestos de un grupo
    List<Presupuesto> findByGrupo(Grupo grupo);

    // Buscar todos los presupuestos de un usuario en un grupo
    List<Presupuesto> findByUsuarioAndGrupo(Usuario usuario, Grupo grupo);

    // Buscar presupuestos por un rango de fechas
    List<Presupuesto> findByFechaInicioBetween(Date fechaInicio, Date fechaFin);

    // Buscar presupuestos por usuario en un rango de fechas
    List<Presupuesto> findByUsuarioAndFechaInicioBetween(Usuario usuario, Date fechaInicio, Date fechaFin);

    // Buscar presupuestos por grupo en un rango de fechas
    List<Presupuesto> findByGrupoAndFechaInicioBetween(Grupo grupo, Date fechaInicio, Date fechaFin);
}

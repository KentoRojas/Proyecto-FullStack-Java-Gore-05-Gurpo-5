package com.grupo5.Repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupo5.modelos.Sueldo;
import com.grupo5.modelos.Usuario;

public interface RepositorioSueldo extends CrudRepository<Sueldo, Long> {

    // Buscar todos los sueldos de un usuario específico
    List<Sueldo> findByUsuario(Usuario usuario);

    // Buscar sueldos por un rango de fechas para un usuario específico
    List<Sueldo> findByUsuarioAndFechaIngresoBetween(Usuario usuario, Date fechaInicio, Date fechaFin);

    // Buscar sueldos por la última fecha de actualización
    List<Sueldo> findByFechaActualizacion(Date fechaActualizacion);

    // Buscar el último sueldo ingresado para un usuario
    Sueldo findTopByUsuarioOrderByFechaIngresoDesc(Usuario usuario);

}

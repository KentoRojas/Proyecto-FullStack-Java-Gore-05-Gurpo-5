package com.grupo5.Repositorios;

<<<<<<< HEAD
import java.time.LocalDate;
=======
import java.util.Date;
>>>>>>> 78c78b8f66f3228de458497368ad1bfa23742559
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupo5.modelos.Sueldo;
import com.grupo5.modelos.Usuario;

public interface RepositorioSueldo extends CrudRepository<Sueldo, Long> {

    // Buscar todos los sueldos de un usuario específico
    List<Sueldo> findByUsuario(Usuario usuario);

    // Buscar sueldos por un rango de fechas para un usuario específico
<<<<<<< HEAD
    List<Sueldo> findByUsuarioAndFechaIngresoBetween(Usuario usuario, LocalDate  fechaInicio, LocalDate  fechaFin);

    // Buscar sueldos por la última fecha de actualización
    List<Sueldo> findByFechaActualizacion(LocalDate  fechaActualizacion);
=======
    List<Sueldo> findByUsuarioAndFechaIngresoBetween(Usuario usuario, Date fechaInicio, Date fechaFin);

    // Buscar sueldos por la última fecha de actualización
    List<Sueldo> findByFechaActualizacion(Date fechaActualizacion);
>>>>>>> 78c78b8f66f3228de458497368ad1bfa23742559

    // Buscar el último sueldo ingresado para un usuario
    Sueldo findTopByUsuarioOrderByFechaIngresoDesc(Usuario usuario);

}

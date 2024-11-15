package com.grupo5.Servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioSueldo;
import com.grupo5.modelos.Sueldo;
import com.grupo5.modelos.Usuario;

@Service
public class ServicioSueldo {
	
	@Autowired
    private RepositorioSueldo repositorioSueldo;

    // Método para registrar un nuevo sueldo
    public Sueldo registrarSueldo(Sueldo sueldo) {
        if (sueldo != null) {
            return repositorioSueldo.save(sueldo);
        }
        return null; // Retorna null si el sueldo es nulo
    }

    // Método para obtener un sueldo por su ID
    public Sueldo obtenerSueldoPorId(Long id) {
        Optional<Sueldo> sueldo = repositorioSueldo.findById(id);
        return sueldo.orElse(null);
    }

    // Método para obtener todos los sueldos de un usuario
    public List<Sueldo> obtenerSueldosPorUsuario(Usuario usuario) {
        return repositorioSueldo.findByUsuario(usuario);
    }

    // Método para obtener sueldos por un rango de fechas para un usuario
    public List<Sueldo> obtenerSueldosPorUsuarioYFecha(Usuario usuario, Date fechaInicio, Date fechaFin) {
        return repositorioSueldo.findByUsuarioAndFechaIngresoBetween(usuario, fechaInicio, fechaFin);
    }

    // Método para eliminar un sueldo por su ID
    public boolean eliminarSueldo(Long id) {
        if (repositorioSueldo.existsById(id)) {
        	repositorioSueldo.deleteById(id);
            return true;
        }
        return false; // Retorna false si el sueldo no existe
    }

    // Método para actualizar un sueldo existente
    public Sueldo actualizarSueldo(Long id, Sueldo sueldoActualizado) {
        Sueldo sueldoExistente = obtenerSueldoPorId(id);
        if (sueldoExistente != null) {
            sueldoExistente.setMonto(sueldoActualizado.getMonto());
            sueldoExistente.setFechaIngreso(sueldoActualizado.getFechaIngreso());
            sueldoExistente.setFechaActualizacion(new Date());
            return repositorioSueldo.save(sueldoExistente);
        }
        return null; // Retorna null si el sueldo no existe
    }

    // Método para obtener el último sueldo ingresado por un usuario
    public Sueldo obtenerUltimoSueldoPorUsuario(Usuario usuario) {
        return repositorioSueldo.findTopByUsuarioOrderByFechaIngresoDesc(usuario);
    }
}

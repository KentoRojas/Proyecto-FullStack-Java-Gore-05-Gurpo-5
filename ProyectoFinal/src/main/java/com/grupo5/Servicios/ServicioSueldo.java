package com.grupo5.Servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    // Método para registrar un monto o crear un nuevo sueldo si no existe
    public Sueldo registrarMonto(Usuario usuario, BigDecimal montoAdicional) {
        Sueldo nuevoRegistro = new Sueldo();
        nuevoRegistro.setUsuario(usuario);
        nuevoRegistro.setMonto(montoAdicional);
        nuevoRegistro.setFechaIngreso(null); // Mantener nulo para distinguir del primer ingreso
        nuevoRegistro.setFechaActualizacion(LocalDate.now());
        return repositorioSueldo.save(nuevoRegistro);
    }

    // Método para registrar o sumar sueldo (acumulativo)
    public Sueldo registrarOSumarSueldo(Usuario usuario, BigDecimal montoAdicional) {
        Sueldo sueldoExistente = repositorioSueldo.findTopByUsuarioOrderByFechaIngresoDesc(usuario);

        if (sueldoExistente != null) {
            sueldoExistente.setMonto(sueldoExistente.getMonto().add(montoAdicional));
            sueldoExistente.setFechaActualizacion(LocalDate.now());
            return repositorioSueldo.save(sueldoExistente);
        } else {
            Sueldo nuevoSueldo = new Sueldo();
            nuevoSueldo.setUsuario(usuario);
            nuevoSueldo.setMonto(montoAdicional);
            nuevoSueldo.setFechaIngreso(LocalDate.now());
            nuevoSueldo.setFechaActualizacion(LocalDate.now());
            return repositorioSueldo.save(nuevoSueldo);
        }
    }

    // Método para obtener el sueldo acumulado de un usuario
    public BigDecimal obtenerSueldoAcumulado(Usuario usuario) {
        List<Sueldo> sueldos = repositorioSueldo.findByUsuario(usuario);
        return sueldos.stream()
                .map(Sueldo::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Base preparada para restar un gasto del sueldo acumulado
    public BigDecimal restarGastoDelSueldo(Usuario usuario, BigDecimal montoGasto) {
        BigDecimal sueldoAcumulado = obtenerSueldoAcumulado(usuario);
        BigDecimal nuevoSueldo = sueldoAcumulado.subtract(montoGasto);

        // Guardar el cambio en la base de datos (pendiente implementar)
        // Por ahora, esta lógica no afecta al resto del proyecto
        return nuevoSueldo.compareTo(BigDecimal.ZERO) >= 0 ? nuevoSueldo : BigDecimal.ZERO;
    }

    // Método para obtener sueldos por usuario
    public List<Sueldo> obtenerSueldosPorUsuario(Usuario usuario) {
        return repositorioSueldo.findByUsuario(usuario);
    }

    // Método para obtener sueldos por rango de fechas
    public List<Sueldo> obtenerSueldosPorUsuarioYFecha(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin) {
        return repositorioSueldo.findByUsuarioAndFechaIngresoBetween(usuario, fechaInicio, fechaFin);
    }

    // Método para eliminar un sueldo por su ID
    public boolean eliminarSueldo(Long id) {
        if (repositorioSueldo.existsById(id)) {
            repositorioSueldo.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para actualizar un sueldo existente
    public Sueldo actualizarSueldo(Long id, Sueldo sueldoActualizado) {
        Sueldo sueldoExistente = obtenerSueldoPorId(id);
        if (sueldoExistente != null) {
            sueldoExistente.setMonto(sueldoActualizado.getMonto());
            sueldoExistente.setFechaIngreso(sueldoActualizado.getFechaIngreso());
            sueldoExistente.setFechaActualizacion(LocalDate.now());
            return repositorioSueldo.save(sueldoExistente);
        }
        return null;
    }

    // Método para obtener un sueldo por su ID
    public Sueldo obtenerSueldoPorId(Long id) {
        Optional<Sueldo> sueldo = repositorioSueldo.findById(id);
        return sueldo.orElse(null);
    }

    // Método para obtener el último sueldo ingresado por un usuario
    public Sueldo obtenerUltimoSueldoPorUsuario(Usuario usuario) {
        return repositorioSueldo.findTopByUsuarioOrderByFechaIngresoDesc(usuario);
    }
}


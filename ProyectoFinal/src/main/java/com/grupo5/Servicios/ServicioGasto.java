package com.grupo5.Servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioGasto;
import com.grupo5.modelos.Categoria;
import com.grupo5.modelos.Gasto;
import com.grupo5.modelos.Usuario;

@Service
public class ServicioGasto {

	@Autowired
    private RepositorioGasto repositorioGasto;

    // Método para registrar un nuevo gasto
    public Gasto registrarGasto(Gasto gasto) {
        if (gasto != null) {
            return repositorioGasto.save(gasto);
        }
        return null; // Retorna null si el gasto es nulo
    }

    // Método para obtener un gasto por su ID
    public Gasto obtenerGastoPorId(Long id) {
        Optional<Gasto> gasto = repositorioGasto.findById(id);
        return gasto.orElse(null);
    }

    // Método para obtener todos los gastos de un usuario
    public List<Gasto> obtenerGastosPorUsuario(Usuario usuario) {
        return repositorioGasto.findByUsuario(usuario);
    }

    // Método para obtener gastos por categoría
    public List<Gasto> obtenerGastosPorCategoria(Categoria categoria) {
        return repositorioGasto.findByTipo(categoria);
    }

    // Método para obtener gastos en un rango de fechas
    public List<Gasto> obtenerGastosPorFecha(Date fechaInicio, Date fechaFin) {
        return repositorioGasto.findByFechaBetween(fechaInicio, fechaFin);
    }

    // Método para obtener los gastos de un usuario en un rango de fechas
    public List<Gasto> obtenerGastosPorUsuarioYFecha(Usuario usuario, Date fechaInicio, Date fechaFin) {
        return repositorioGasto.findByUsuarioAndFechaBetween(usuario, fechaInicio, fechaFin);
    }

    // Método para eliminar un gasto por su ID
    public boolean eliminarGasto(Long id) {
        if (repositorioGasto.existsById(id)) {
        	repositorioGasto.deleteById(id);
            return true;
        }
        return false; // Retorna false si el gasto no existe
    }

    // Método para actualizar un gasto existente
    public Gasto actualizarGasto(Long id, Gasto gastoActualizado) {
        Gasto gastoExistente = obtenerGastoPorId(id);
        if (gastoExistente != null) {
            gastoExistente.setMonto(gastoActualizado.getMonto());
            gastoExistente.setFecha(gastoActualizado.getFecha());
            gastoExistente.setTipo(gastoActualizado.getTipo());
            gastoExistente.setBoleta(gastoActualizado.getBoleta());
            return repositorioGasto.save(gastoExistente);
        }
        return null; // Retorna null si el gasto no existe
    }
}

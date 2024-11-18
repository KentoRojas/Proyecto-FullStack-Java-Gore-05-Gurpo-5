package com.grupo5.Servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioBoleta;
import com.grupo5.modelos.Boleta;
import com.grupo5.modelos.Gasto;

@Service
public class ServicioBoleta {

	@Autowired
	private RepositorioBoleta repositorioBoleta;
	
	 // Método para registrar una nueva boleta
    public Boleta registrarBoleta(Boleta boleta) {
        if (boleta != null) {
            return repositorioBoleta.save(boleta);
        }
        return null; // Retorna null si la boleta es nula
    }

    // Método para obtener una boleta por su ID
    public Boleta obtenerBoletaPorId(Long id) {
        Optional<Boleta> boleta = repositorioBoleta.findById(id);
        return boleta.orElse(null);
    }

    // Método para obtener todas las boletas asociadas a un gasto
    public List<Boleta> obtenerBoletasPorGasto(Gasto gasto) {
        return repositorioBoleta.findByGasto(gasto);
    }

    // Método para obtener boletas por fecha de emisión
    public List<Boleta> obtenerBoletasPorFechaEmision(Date fechaEmision) {
        return repositorioBoleta.findByFechaEmision(fechaEmision);
    }

    // Método para obtener boletas por fecha de subida
    public List<Boleta> obtenerBoletasPorFechaSubida(Date fechaSubida) {
        return repositorioBoleta.findByFechaSubida(fechaSubida);
    }

    // Método para obtener boletas por un rango de fechas de emisión
    public List<Boleta> obtenerBoletasPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        return repositorioBoleta.findByFechaEmisionBetween(fechaInicio, fechaFin);
    }

    // Método para eliminar una boleta por su ID
    public boolean eliminarBoleta(Long id) {
        if (repositorioBoleta.existsById(id)) {
        	repositorioBoleta.deleteById(id);
            return true;
        }
        return false; // Retorna false si la boleta no existe
    }

    // Método para actualizar una boleta existente
    public Boleta actualizarBoleta(Long id, Boleta boletaActualizada) {
        Boleta boletaExistente = obtenerBoletaPorId(id);
        if (boletaExistente != null) {
            boletaExistente.setImagen(boletaActualizada.getImagen());
            boletaExistente.setFechaEmision(boletaActualizada.getFechaEmision());
            boletaExistente.setDescripcion(boletaActualizada.getDescripcion());
            boletaExistente.setGasto(boletaActualizada.getGasto());
            return repositorioBoleta.save(boletaExistente);
        }
        return null; // Retorna null si la boleta no existe
    }
}

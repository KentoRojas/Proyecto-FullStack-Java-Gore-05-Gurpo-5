package com.grupo5.Repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo5.modelos.Boleta;
import com.grupo5.modelos.Gasto;

@Repository
public interface RepositorioBoleta extends CrudRepository<Boleta, Long>  {

	// Buscar todas las boletas asociadas a un gasto específico
    List<Boleta> findByGasto(Gasto gasto);

    // Buscar boletas por la fecha de emisión
    List<Boleta> findByFechaEmision(Date fechaEmision);

    // Buscar boletas por la fecha de subida
    List<Boleta> findByFechaSubida(Date fechaSubida);

    // Buscar boletas por un rango de fechas de emisión
    List<Boleta> findByFechaEmisionBetween(Date fechaInicio, Date fechaFin);

    // Buscar boletas por un rango de fechas de subida
    List<Boleta> findByFechaSubidaBetween(Date fechaInicio, Date fechaFin);

    // Buscar boletas con una descripción específica
    List<Boleta> findByDescripcionContaining(String descripcion);
}

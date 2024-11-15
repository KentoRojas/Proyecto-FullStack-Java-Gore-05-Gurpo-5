package com.grupo5.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo5.modelos.Categoria;

@Repository
public interface RepositorioCategoria extends CrudRepository<Categoria, Long> {
	
	// Buscar una categoria por su nombre 
	Optional<Categoria> findByNombreCategoria(String nombreCategoria);
	
	// Verificar si existe una categoria con ese nombre
	boolean existsByNombreCategoria(String nombreCategoria);
	
	// Buscar las categorias por si prioridad 
	List<Categoria> findByPrioridad(Categoria.Prioridad prioridad);

}

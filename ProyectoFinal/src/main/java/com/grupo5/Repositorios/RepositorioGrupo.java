package com.grupo5.Repositorios;

import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioGrupo extends CrudRepository<Grupo, Long> {
	
	// Buscar todos los grupos ordenados por fecha de creación
    List<Grupo> findAllByOrderByFechaCreacionDesc();

    // Buscar grupos por un usuario específico (basado en una relación si existe)
    List<Grupo> findByMiembrosUsuario(Usuario usuario);
    
    // Hereda findAll() por defecto
    List<Grupo> findAll();
}

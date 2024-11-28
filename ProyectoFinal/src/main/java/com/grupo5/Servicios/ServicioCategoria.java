package com.grupo5.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioCategoria;
import com.grupo5.modelos.Categoria;

@Service
public class ServicioCategoria {

	@Autowired
	private RepositorioCategoria repositorioCategoria;
	
	// Métodos para registrar una nueva categoria
	public Categoria registrarCategorias(Categoria categoria) {
		// Validar si la categoria ya existe por si nombre
		if(repositorioCategoria.findByNombreCategoria(categoria.getNombreCategoria()).isPresent()) {
			return null; // retorna null si ya existe la categoria
		}
		// Guardar la categoria
		return repositorioCategoria.save(categoria);
	}
	
	// Obtener categoría por ID con validación
    public Categoria obtenerCategoriaPorID(Long id) {
        return repositorioCategoria.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("La categoría con ID " + id + " no existe."));
    }
	
    // Obtener categoría por nombre con validación
    public Categoria obtenerCategoriaPorNombre(String nombre) {
        return repositorioCategoria.findByNombreCategoria(nombre)
            .orElseThrow(() -> new IllegalArgumentException("La categoría con nombre " + nombre + " no existe."));
    }

    // Obtener todas las categorías
    public List<Categoria> obtenerTodasLasCategorias() {
        return (List<Categoria>) repositorioCategoria.findAll();
    }

    // Método para buscar categorías por prioridad (Alta, Media, Baja)
    public List<Categoria> obtenerCategoriasPorPrioridad(Categoria.Prioridad prioridad) {
        return repositorioCategoria.findByPrioridad(prioridad);
    }
    
    // Método para eliminar una categoria por id
    public boolean eliminarCategoria(Long id) {
    	if(repositorioCategoria.existsById(id)) {
    		repositorioCategoria.deleteById(id);
    		return true;
    	}
    	return false; //retorna falso si la categoria no existe
    }
}
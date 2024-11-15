package com.grupo5.Repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.grupo5.modelos.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
    
    // Buscar un usuario por su correo
    Optional<Usuario> findByCorreo(String correo);
    
    // Buscar usuario por nombre de usuario (ajustado)
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
    // Verificar si ya existe un correo
    boolean existsByCorreo(String correo);
    
    // Verificar si ya existe un nombre de usuario (ajustado)
    boolean existsByNombreUsuario(String nombreUsuario);
}

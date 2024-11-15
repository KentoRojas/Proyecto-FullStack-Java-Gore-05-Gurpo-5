package com.grupo5.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioUsuario;
import com.grupo5.modelos.Usuario;

@Service
public class ServiciosUsuario {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	// Método para registrar un nuevo usuario
	public Usuario registrarUsuario(Usuario usuario) {
		// Validar si el correo ya está registrado
		if(repositorioUsuario.findByCorreo(usuario.getCorreo()).isPresent()) {
			return null; // retorna null si el correo ya existe
		}
		// Validar si el nombre de usuario ya esta registrado
		if(repositorioUsuario.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
			return null; // retirna null si el nombre de usuario ya existe
		}
		// Guardar el usuario
		return repositorioUsuario.save(usuario);
	}
	
	// Metodo para buscar el usuario por su correo
	public Usuario obtenerUsuarioPorCorreo(String correo) {
		return repositorioUsuario.findByCorreo(correo).orElse(null);
	}
	
	// Método para buscar un usuario por el nombre de usuario
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return repositorioUsuario.findByNombreUsuario(nombreUsuario).orElse(null);
	}
	
	// Método para obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuario = repositorioUsuario.findById(id);
        return usuario.orElse(null);
    }
    
    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios(){
    	return (List<Usuario>) repositorioUsuario.findAll();
    }
    
    // Método para validar el login (sin encriptación)
    public boolean validarLogin(String correo, String contraseña) {
        Usuario usuario = obtenerUsuarioPorCorreo(correo);
        // Verificar si el usuario existe y si la contraseña coincide
        if (usuario != null && usuario.getPassword().equals(contraseña)) {
            return true;
        }
        return false; // Credenciales incorrectas
    }
}

package com.grupo5.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.Repositorios.RepositorioMiembroGrupo;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.MiembroGrupo;
import com.grupo5.modelos.MiembroGrupoId;
import com.grupo5.modelos.Usuario;

@Service
public class ServicioMiembroGrupo {

	@Autowired
	private RepositorioMiembroGrupo repositorioMiembroGrupo;
	
	// Método para asignar un usuario a un grupo con un rol
    public MiembroGrupo registrarMiembroGrupo(MiembroGrupo miembroGrupo) {
        if (miembroGrupo != null) {
            return repositorioMiembroGrupo.save(miembroGrupo);
        }
        return null; // Retorna null si el objeto miembroGrupo es nulo
    }

    // Método para obtener un miembro de un grupo por su ID compuesto
    public MiembroGrupo obtenerMiembroPorId(MiembroGrupoId id) {
        Optional<MiembroGrupo> miembro = repositorioMiembroGrupo.findById(id);
        return miembro.orElse(null);
    }

    // Método para obtener todos los miembros de un grupo
    public List<MiembroGrupo> obtenerMiembrosPorGrupo(Grupo grupo) {
        return repositorioMiembroGrupo.findByGrupo(grupo);
    }

    // Método para obtener todos los grupos a los que pertenece un usuario
    public List<MiembroGrupo> obtenerGruposPorUsuario(Usuario usuario) {
        return repositorioMiembroGrupo.findByUsuario(usuario);
    }

    // Método para obtener miembros de un grupo con un rol específico
    public List<MiembroGrupo> obtenerMiembrosPorGrupoYRol(Grupo grupo, MiembroGrupo.Rol rol) {
        return repositorioMiembroGrupo.findByGrupoAndRol(grupo, rol);
    }

    // Método para verificar si un usuario es administrador de un grupo
    public boolean esAdministrador(Usuario usuario, Grupo grupo) {
        return repositorioMiembroGrupo.existsByUsuarioAndGrupoAndRol(usuario, grupo, MiembroGrupo.Rol.Admin);
    }

    // Método para eliminar un miembro de un grupo
    public boolean eliminarMiembro(MiembroGrupoId id) {
        if (repositorioMiembroGrupo.existsById(id)) {
        	repositorioMiembroGrupo.deleteById(id);
            return true;
        }
        return false; // Retorna false si el miembro no existe
    }

    // Método para actualizar el rol de un miembro en un grupo
    public MiembroGrupo actualizarRolDeMiembro(MiembroGrupoId id, MiembroGrupo.Rol nuevoRol) {
        MiembroGrupo miembroExistente = obtenerMiembroPorId(id);
        if (miembroExistente != null) {
            miembroExistente.setRol(nuevoRol);
            return repositorioMiembroGrupo.save(miembroExistente);
        }
        return null; // Retorna null si el miembro no existe
    }
}

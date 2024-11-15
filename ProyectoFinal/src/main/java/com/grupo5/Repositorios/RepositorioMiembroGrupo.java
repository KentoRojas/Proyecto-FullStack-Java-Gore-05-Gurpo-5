package com.grupo5.Repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.MiembroGrupo;
import com.grupo5.modelos.MiembroGrupoId;
import com.grupo5.modelos.Usuario;

@Repository
public interface RepositorioMiembroGrupo extends CrudRepository<MiembroGrupo, MiembroGrupoId> {

    // Buscar todos los miembros de un grupo específico
    List<MiembroGrupo> findByGrupo(Grupo grupo);

    // Buscar todos los grupos a los que pertenece un usuario
    List<MiembroGrupo> findByUsuario(Usuario usuario);

    // Buscar todos los miembros de un grupo con un rol específico
    List<MiembroGrupo> findByGrupoAndRol(Grupo grupo, MiembroGrupo.Rol rol);

    // Buscar si un usuario es administrador de un grupo específico
    boolean existsByUsuarioAndGrupoAndRol(Usuario usuario, Grupo grupo, MiembroGrupo.Rol rol);

    // Buscar si un usuario ya está en un grupo
    boolean existsByUsuarioAndGrupo(Usuario usuario, Grupo grupo);

}

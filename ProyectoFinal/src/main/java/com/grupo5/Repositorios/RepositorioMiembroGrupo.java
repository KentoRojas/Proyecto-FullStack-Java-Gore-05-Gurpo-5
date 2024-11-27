package com.grupo5.Repositorios;

import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.MiembroGrupo;
import com.grupo5.modelos.Usuario;
import com.grupo5.modelos.MiembroGrupoId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioMiembroGrupo extends JpaRepository<MiembroGrupo, MiembroGrupoId> {
	
    List<MiembroGrupo> findByUsuario(Usuario usuario);

    // Obtener todos los miembros de un grupo
    List<MiembroGrupo> findByGrupo(Grupo grupo);

    // Buscar un miembro específico en un grupo
    MiembroGrupo findByGrupoAndUsuario(Grupo grupo, Usuario usuario);

    // Verificar si un miembro pertenece a un grupo por su ID compuesto
    boolean existsById(MiembroGrupoId id);
}

package com.grupo5.Servicios;

import com.grupo5.Repositorios.RepositorioMiembroGrupo;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.MiembroGrupo;
import com.grupo5.modelos.MiembroGrupoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMiembroGrupo {

    @Autowired
    private RepositorioMiembroGrupo repositorioMiembroGrupo;

    @Autowired
    private ServicioGrupo servicioGrupo;
    
 // Obtener un miembro por su ID compuesto
    public MiembroGrupo obtenerMiembroPorId(MiembroGrupoId id) {
        Optional<MiembroGrupo> miembro = repositorioMiembroGrupo.findById(id);
        return miembro.orElse(null); // Retorna null si no se encuentra
    }

    // Obtener miembros de un grupo
    public List<MiembroGrupo> listarMiembrosPorGrupo(Long idGrupo) {
        Grupo grupo = servicioGrupo.obtenerGrupoPorId(idGrupo);
        return repositorioMiembroGrupo.findByGrupo(grupo);
    }

    // Registrar un nuevo miembro
    public MiembroGrupo registrarMiembroGrupo(MiembroGrupo miembroGrupo) {
        return repositorioMiembroGrupo.save(miembroGrupo);
    }

    // Eliminar un miembro
    public boolean eliminarMiembro(Long idUsuario, Long idGrupo) {
        Optional<MiembroGrupo> miembro = repositorioMiembroGrupo.findById(new MiembroGrupoId(idUsuario, idGrupo));
        if (miembro.isPresent()) {
            repositorioMiembroGrupo.delete(miembro.get());
            return true;
        }
        return false;
    }

    // Cambiar rol de un miembro
    public boolean cambiarRolDeMiembro(Long idUsuario, Long idGrupo, String nuevoRol) {
        Optional<MiembroGrupo> miembro = repositorioMiembroGrupo.findById(new MiembroGrupoId(idUsuario, idGrupo));
        if (miembro.isPresent()) {
            MiembroGrupo miembroGrupo = miembro.get();
            miembroGrupo.setRol(MiembroGrupo.Rol.valueOf(nuevoRol.toUpperCase()));
            repositorioMiembroGrupo.save(miembroGrupo);
            return true;
        }
        return false;
    }
}

package com.grupo5.Servicios;

import com.grupo5.Repositorios.RepositorioCategoria;
import com.grupo5.Repositorios.RepositorioGrupo;
import com.grupo5.modelos.Categoria;
import com.grupo5.modelos.Grupo;
import com.grupo5.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioGrupo {

    @Autowired
    private RepositorioGrupo repositorioGrupo;

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    // Crear un nuevo grupo
    public Grupo crearGrupo(Grupo grupo, Usuario usuarioCreador) {
        if (grupo.getNombreGrupo() == null || grupo.getNombreGrupo().isEmpty()) {
            throw new IllegalArgumentException("El nombre del grupo no puede estar vacío");
        }
        if (grupo.getPresupuestoTotal() == null || grupo.getPresupuestoTotal() <= 0) {
            throw new IllegalArgumentException("El presupuesto total debe ser mayor que cero");
        }
        grupo.setFechaCreacion(LocalDate.now()); // Fecha actual
        return repositorioGrupo.save(grupo);
    }

    // Obtener grupos por usuario
    public List<Grupo> obtenerGruposPorUsuario(Usuario usuario) {
        return repositorioGrupo.findByMiembrosUsuario(usuario);
    }

    // Añadir presupuesto al grupo
    public boolean agregarPresupuesto(Long idGrupo, Integer monto) {
        Grupo grupo = repositorioGrupo.findById(idGrupo).orElse(null);

        if (grupo == null) {
            return false; // Grupo no encontrado
        }

        // Actualizar el presupuesto total
        Integer presupuestoActual = grupo.getPresupuestoTotal();
        grupo.setPresupuestoTotal(presupuestoActual + monto);

        repositorioGrupo.save(grupo);
        return true; // Operación exitosa
    }

    // Método para obtener una categoría por ID
    public Categoria obtenerCategoriaPorId(Long idCategoria) {
        return repositorioCategoria.findById(idCategoria).orElse(null);
    }

    // Obtener un grupo por ID
    public Grupo obtenerGrupoPorId(Long idGrupo) {
        return repositorioGrupo.findById(idGrupo).orElse(null);
    }

    public List<Grupo> obtenerGrupos() {
        return repositorioGrupo.findAll(); // Asegúrate de tener este método en el repositorio
    }
}
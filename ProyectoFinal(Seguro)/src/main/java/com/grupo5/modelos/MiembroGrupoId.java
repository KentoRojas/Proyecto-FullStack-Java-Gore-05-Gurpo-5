package com.grupo5.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MiembroGrupoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_grupo")
    private Long idGrupo;

    // Constructor vacío
    public MiembroGrupoId() {}

    // Constructor con parámetros
    public MiembroGrupoId(Long idUsuario, Long idGrupo) {
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiembroGrupoId that = (MiembroGrupoId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idGrupo, that.idGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idGrupo);
    }
}

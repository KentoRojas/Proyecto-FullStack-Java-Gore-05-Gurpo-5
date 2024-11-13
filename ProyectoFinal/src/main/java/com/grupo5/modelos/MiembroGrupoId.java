package com.grupo5.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MiembroGrupoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "id_grupo")
    private int idGrupo;

    public MiembroGrupoId() {}

    public MiembroGrupoId(int idUsuario, int idGrupo) {
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiembroGrupoId that = (MiembroGrupoId) o;
        return idUsuario == that.idUsuario && idGrupo == that.idGrupo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idGrupo);
    }
}

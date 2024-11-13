package com.grupo5.modelos;

import jakarta.persistence.*;

@Entity
public class MiembroGrupo {

    @EmbeddedId
    private MiembroGrupoId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    public enum Rol {
        Admin,
        Miembro
    }


    public MiembroGrupoId getId() {
        return id;
    }

    public void setId(MiembroGrupoId id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}

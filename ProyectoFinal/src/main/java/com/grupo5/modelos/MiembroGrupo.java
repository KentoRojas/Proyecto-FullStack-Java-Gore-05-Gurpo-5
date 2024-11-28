package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "MiembrosGrupo")
public class MiembroGrupo {

    @EmbeddedId
    private MiembroGrupoId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "El rol no puede estar vacío")
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    // Enum para los roles
    public enum Rol {
        Admin,
        Miembro
    }

    // Constructor vacío
    public MiembroGrupo() {}

    // Constructor con parámetros
    public MiembroGrupo(MiembroGrupoId id, Rol rol, Usuario usuario, Grupo grupo) {
        this.id = id;
        this.rol = rol;
        this.usuario = usuario;
        this.grupo = grupo;
    }

    // Getters y Setters
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
package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id_grupo;

    @NotBlank(message = "El nombre del grupo no puede estar vacío")
    @Size(max = 100, message = "El nombre del grupo no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre_grupo;

    @NotNull(message = "El presupuesto total no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El presupuesto total debe ser mayor que cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal presupuesto_total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Date fecha_creacion;

    // Relación con MiembroGrupo (Un grupo tiene muchos miembros)
    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MiembroGrupo> miembros;

    // Relación con Presupuesto (Un grupo puede tener múltiples presupuestos)
    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    // Constructor vacío
    public Grupo() {
        this.fecha_creacion = new Date(); // Fecha de creación por defecto al momento de crear un grupo
    }

    // Getters y Setters
    public Long getId_grupo() {
        return id_grupo;
    }
    public void setId_grupo(Long id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }
    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public BigDecimal getPresupuesto_total() {
        return presupuesto_total;
    }
    public void setPresupuesto_total(BigDecimal presupuesto_total) {
        this.presupuesto_total = presupuesto_total;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public List<MiembroGrupo> getMiembros() {
        return miembros;
    }
    public void setMiembros(List<MiembroGrupo> miembros) {
        this.miembros = miembros;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }
    public void setPresupuestos(List<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }
}


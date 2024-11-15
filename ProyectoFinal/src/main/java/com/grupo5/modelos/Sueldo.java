package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Sueldos")
public class Sueldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sueldo")
    private Long id_sueldo;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private BigDecimal monto;

    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;

    // Relación con Usuario (Muchos sueldos para un usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    // Constructor vacío
    public Sueldo() {
        this.fechaActualizacion = new Date(); // Fecha de actualización por defecto
    }

    // Getters y Setters
    public Long getId_sueldo() {
        return id_sueldo;
    }
    public void setId_sueldo(Long id_sueldo) {
        this.id_sueldo = id_sueldo;
    }

    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


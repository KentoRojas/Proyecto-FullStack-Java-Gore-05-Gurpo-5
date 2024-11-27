package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "fechaIngreso", nullable = false, updatable = false)
    private LocalDate fechaIngreso;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    // Relación con Usuario (Muchos sueldos para un usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    // Constructor vacío
    public Sueldo() {
    }

    // Asignar automáticamente la fecha de ingreso al crear el registro
    @PrePersist
    protected void onCreate() {
        this.fechaIngreso = LocalDate.now(); // Fecha actual al registrar
        this.fechaActualizacion = LocalDate.now(); // También inicializa la fecha de actualización
    }

    // Actualizar la fecha de actualización al modificar el registro
    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDate.now(); // Fecha actual al actualizar
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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


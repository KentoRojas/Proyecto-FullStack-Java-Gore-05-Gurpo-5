package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Presupuestos")
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private Long id_presupuesto;

    @NotNull(message = "El monto máximo no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto máximo debe ser mayor que cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montoMaximo;

    @NotNull(message = "La fecha de inicio no puede estar vacía")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @NotNull(message = "La fecha de fin no puede estar vacía")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    // Relación con Usuario (Muchos presupuestos para un usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    // Relación con Grupo (Muchos presupuestos para un grupo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupos", nullable = false)
    private Grupo grupo;

    // Constructor vacío
    public Presupuesto() {}

    // Getters y Setters
    public Long getId_presupuesto() {
        return id_presupuesto;
    }
    public void setId_presupuesto(Long id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }
    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

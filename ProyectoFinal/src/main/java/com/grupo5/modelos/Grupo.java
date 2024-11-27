package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long idGrupo;

    @NotBlank(message = "El nombre del grupo no puede estar vacío")
    @Size(max = 100, message = "El nombre del grupo no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreGrupo;

    @NotNull(message = "El presupuesto total no puede estar vacío")
    @Min(value = 1, message = "El presupuesto total debe ser mayor que cero")
    @Column(nullable = false)
    private Integer presupuestoTotal;

    @NotNull(message = "El presupuesto restante no puede estar vacío")
    @Min(value = 0, message = "El presupuesto restante no puede ser negativo")
    @Column(nullable = false)
    private Integer presupuestoRestante;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MiembroGrupo> miembros;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Gasto> gastos;

    // Constructor vacío
    public Grupo() {
        this.fechaCreacion = LocalDate.now(); // Fecha actual
        this.presupuestoRestante = 0; // Inicializa en cero
    }

    // Getters y Setters
    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Integer getPresupuestoTotal() {
        return presupuestoTotal;
    }

    public void setPresupuestoTotal(Integer presupuestoTotal) {
        this.presupuestoTotal = presupuestoTotal;
        if (this.presupuestoRestante == 0) {
            this.presupuestoRestante = presupuestoTotal; // Inicializa el presupuesto restante si no está definido
        }
    }

    public Integer getPresupuestoRestante() {
        return presupuestoRestante;
    }

    public void setPresupuestoRestante(Integer presupuestoRestante) {
        this.presupuestoRestante = presupuestoRestante;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    // Métodos de negocio
    public boolean registrarGasto(Integer monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        if (presupuestoRestante < monto) {
            return false; // No se puede registrar el gasto si supera el presupuesto restante
        }

        presupuestoRestante -= monto;
        return true;
    }

    public void incrementarPresupuesto(Integer monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        presupuestoTotal += monto;
        presupuestoRestante += monto;
    }
}
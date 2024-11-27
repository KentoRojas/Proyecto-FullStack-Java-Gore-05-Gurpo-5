package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "Gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Gasto")
    private Long idGasto;

    @NotNull(message = "El monto no puede estar vacío")
    @Positive(message = "El monto debe ser mayor que cero")
    @Column(nullable = false)
    private Integer monto;

    @NotNull(message = "La fecha no puede estar vacía")
    @Column(nullable = false)
    private LocalDate fecha;

    // Relación con Categoria (Un gasto pertenece a una categoría)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo", nullable = false)
    private Categoria tipo;

    // Relación con Usuario (Un gasto pertenece a un usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    // Relación con Grupo (Un gasto pertenece a un grupo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    // Ruta o URL de la imagen de la boleta
    @Size(max = 255, message = "La ruta de la boleta no puede tener más de 255 caracteres")
    private String boleta;

    // Descripción adicional del gasto en la boleta
    @Column(length = 500)
    private String descripcion;

    // Fecha de creación de la boleta
    @NotNull(message = "La fecha de creación de la boleta no puede estar vacía")
    @Column(nullable = false)
    private LocalDate fechaCreacionBoleta;

    // Constructor vacío
    public Gasto() {
        this.fechaCreacionBoleta = LocalDate.now(); // Se establece la fecha actual por defecto
    }

    // Getters y Setters
    public Long getIdGasto() {
        return idGasto;
    }
    public void setIdGasto(Long idGasto) {
        this.idGasto = idGasto;
    }

    public Integer getMonto() {
        return monto;
    }
    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Categoria getTipo() {
        return tipo;
    }
    public void setTipo(Categoria tipo) {
        this.tipo = tipo;
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

    public String getBoleta() {
        return boleta;
    }
    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacionBoleta() {
        return fechaCreacionBoleta;
    }
    public void setFechaCreacionBoleta(LocalDate fechaCreacionBoleta) {
        this.fechaCreacionBoleta = fechaCreacionBoleta;
    }
}
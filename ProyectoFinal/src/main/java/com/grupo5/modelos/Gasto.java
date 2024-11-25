package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Gasto")
    private Long idGasto;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @NotNull(message = "La fecha no puede estar vacía")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    // Relación con Categoria (Un gasto pertenece a una categoría)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo", nullable = false)
    private Categoria tipo;

    // Relación con Usuario (Un gasto pertenece a un usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    // Ruta o URL de la imagen de la boleta
    @Size(max = 255, message = "La ruta de la boleta no puede tener más de 255 caracteres")
    private byte[] boleta;

    // Descripción adicional del gasto en la boleta
    @Column(length = 500)
    private String descripcion;

    // Fecha de creación de la boleta
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacionBoleta;

    // Constructor vacío
    public Gasto() {
        this.fechaCreacionBoleta = new Date(); // Se establece la fecha actual por defecto
    }

    // Getters y Setters
    public Long getIdGasto() {
        return idGasto;
    }
    public void setIdGasto(Long idGasto) {
        this.idGasto = idGasto;
    }

    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
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

    public @Size(max = 255, message = "La ruta de la boleta no puede tener más de 255 caracteres") @Size(max = 255, message = "La ruta de la boleta no puede tener más de 255 caracteres") @Size(max = 255, message = "La ruta de la boleta no puede tener más de 255 caracteres") byte[] getBoleta() {
        return boleta;
    }
    public void setBoleta(byte[] bs) {
        this.boleta = bs;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacionBoleta() {
        return fechaCreacionBoleta;
    }
    public void setFechaCreacionBoleta(Date fechaCreacionBoleta) {
        this.fechaCreacionBoleta = fechaCreacionBoleta;
    }
}


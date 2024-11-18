package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Long idBoleta;

    // Ruta de la imagen en el servidor
    @NotBlank(message = "La imagen no puede estar vacía")
    @Size(max = 255, message = "La ruta de la imagen no puede tener más de 255 caracteres")
    @Column(nullable = false)
    private String imagen;

    // Fecha en la que se subió la boleta al sistema
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_subida", nullable = false)
    private Date fechaSubida;

    // Fecha de emisión de la boleta
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de emisión no puede estar vacía")
    @Column(name = "fecha_emision", nullable = false)
    private Date fechaEmision;

    // Descripción adicional de la boleta
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;

    // Relación con Gasto (una boleta está asociada a un gasto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Gasto", nullable = false)
    private Gasto gasto;

    // Constructor vacío
    public Boleta() {
        this.fechaSubida = new Date(); // Se establece la fecha actual por defecto al subir la boleta
    }

    // Getters y Setters
    public Long getIdBoleta() {
        return idBoleta;
    }
    public void setIdBoleta(Long idBoleta) {
        this.idBoleta = idBoleta;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }
    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Gasto getGasto() {
        return gasto;
    }
    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }
}

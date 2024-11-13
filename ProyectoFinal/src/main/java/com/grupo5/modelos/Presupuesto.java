package com.grupo5.modelos;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_presupuesto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto_maximo;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha_inicio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha_fin;

    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_grupos")
    private Grupo grupo;

    // Getters y Setters

    public int getId_presupuesto() {
        return id_presupuesto;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public BigDecimal getMonto_maximo() {
        return monto_maximo;
    }

    public void setMonto_maximo(BigDecimal monto_maximo) {
        this.monto_maximo = monto_maximo;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
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

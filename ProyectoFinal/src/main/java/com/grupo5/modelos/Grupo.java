package com.grupo5.modelos;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_grupo;

    @Column(nullable = false, length = 100)
    private String nombre_grupo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal presupuesto_total;

    @Column(nullable = false)
    private  Date fecha_creacion;
    

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
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
}

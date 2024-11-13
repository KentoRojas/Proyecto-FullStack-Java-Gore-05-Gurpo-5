package com.grupo5.modelos;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
	public class Sueldo {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id_sueldo;
	    private BigDecimal monto;
	    private Date fechaIngreso;
	    private Date fecha_actualizacion;

	    @ManyToOne
	    @JoinColumn(name = "id_usuarios")
	    private Usuario usuario;

		public int getId_sueldo() {
			return id_sueldo;
		}

		public void setId_sueldo(int id_sueldo) {
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

		public Date getFecha_actualizacion() {
			return fecha_actualizacion;
		}

		public void setFecha_actualizacion(Date fecha_actualizacion) {
			this.fecha_actualizacion = fecha_actualizacion;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
	    
	    
}

package com.grupo5.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorias")
    private Long idCategorias;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 100, message = "El nombre de la categoría no puede tener más de 100 caracteres")
    @Column(name = "nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridad prioridad;

    // Relación con Gasto (Una categoría puede tener muchos gastos)
    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Gasto> gastos;

    // Enum para las prioridades
    public enum Prioridad {
        Alta,
        Media,
        Baja
    }

    // Constructor vacío
    public Categoria() {}

    // Getters y Setters
    public Long getIdCategorias() {
        return idCategorias;
    }
    public void setIdCategorias(Long idCategorias) {
        this.idCategorias = idCategorias;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }
    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }
}

package com.grupo5.Repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo5.modelos.Categoria;
import com.grupo5.modelos.Gasto;
import com.grupo5.modelos.Usuario;

@Repository
public interface RepositorioGasto extends CrudRepository<Gasto, Long> {

    // Buscar todos los gastos por usuario
    List<Gasto> findByUsuario(Usuario usuario);

    // Buscar gastos por categoria
    List<Gasto> findByTipo(Categoria tipo);
    
    // Buscar gasto por usuario y categoria
    List<Gasto> findByUsuarioAndTipo(Usuario usuario, Categoria tipo);
    
    // Buscar gastos por un rango de fechas
    List<Gasto> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Buscar gastos por usuario y un rango de fechas
    List<Gasto> findByUsuarioAndFechaBetween(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin);
    
    // Buscar todos los gastos por grupo
    List<Gasto> findByGrupo_IdGrupo(Long idGrupo);
    
    List<Gasto> findByMontoBetween(Integer min, Integer max);


}
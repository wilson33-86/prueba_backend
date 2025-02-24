package com.company.prueba.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.prueba.model.Comerciante;

public interface IComercianteRepository extends JpaRepository<Comerciante, Integer> {

    @Query("SELECT DISTINCT c.comMunicipio FROM Comerciante c")
    List<String> findDistinctMunicipio();
}

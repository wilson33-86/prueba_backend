package com.company.prueba.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.prueba.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsuEmail(String email);
}

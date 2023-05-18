package com.dev.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}

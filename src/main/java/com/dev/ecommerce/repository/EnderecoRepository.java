package com.dev.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}

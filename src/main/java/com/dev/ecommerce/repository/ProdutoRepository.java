package com.dev.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}

package com.dev.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

}

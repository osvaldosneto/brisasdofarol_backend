package com.osn.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osn.locadora.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

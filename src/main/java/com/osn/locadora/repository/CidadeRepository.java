package com.osn.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osn.locadora.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}

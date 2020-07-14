package com.osn.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();

}

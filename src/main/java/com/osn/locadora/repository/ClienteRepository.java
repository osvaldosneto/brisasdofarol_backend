package com.osn.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//biuscando pelo campo email
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly = true)
	public List<Cliente> findAllByOrderByNomeAsc();
	
	
	@Query("SELECT obj FROM Cliente obj WHERE obj.nome LIKE %:nome% ORDER BY obj.nome")
	List<Cliente> findAllClienteByNome(@Param("nome") String nome);

}

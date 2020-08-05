package com.osn.locadora.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Custos;

public interface CustosRepository extends JpaRepository<Custos, Long>{
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Custos obj where obj.dataPagamento >= :date1 and obj.dataPagamento <= :date2 and obj.nome = :nome ORDER BY obj.id")
	public List<Custos> findByDateAndName(@Param("date1")LocalDate date1, @Param("date2")LocalDate date2, @Param("nome")String nome);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Custos obj where obj.dataPagamento >= :date1 and obj.dataPagamento <= :date2 ORDER BY obj.id")
	public List<Custos> findByDate(LocalDate date1, LocalDate date2);

}
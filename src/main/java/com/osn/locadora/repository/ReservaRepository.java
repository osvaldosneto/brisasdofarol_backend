package com.osn.locadora.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj where obj.checkIn >= :date1 and obj.checkIn <= :date2 ORDER BY obj.id")
	public List<Reserva> findByCreatedDateBetween(@Param("date1")LocalDate date1, @Param("date2")LocalDate date2);
	
}

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
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj where obj.checkIn <= :data and obj.checkOut >= :data ORDER BY obj.id")
	public List<Reserva> findByDate(@Param("data")LocalDate data);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj where obj.cliente.id = :idCliente and obj.hospedagem.id = :idHospedagem ORDER BY obj.id")
	public List<Reserva> findByHospedagemCliente(@Param("idCliente")Long idCliente, @Param("idHospedagem")Long idHospedagem);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj where obj.cliente.id = :idCliente ORDER BY obj.id")
	public List<Reserva> findBycliente(@Param("idCliente")Long clienteId);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj where obj.hospedagem.id = :idHospedagem ORDER BY obj.id")
	public List<Reserva> findByhospedagem(@Param("idHospedagem")Long hospedagemId);
	
}

package com.osn.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osn.locadora.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}

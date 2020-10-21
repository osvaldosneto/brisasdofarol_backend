package com.osn.locadora.repository;

import com.osn.locadora.domain.Jardim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface JardimRepository extends JpaRepository<Jardim, Long> {

    @Transactional(readOnly=true)
    public Jardim findFirstByOrderByIdDesc();

}

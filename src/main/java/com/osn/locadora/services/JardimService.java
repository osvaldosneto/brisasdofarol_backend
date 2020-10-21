package com.osn.locadora.services;

import com.osn.locadora.domain.Jardim;
import com.osn.locadora.repository.JardimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JardimService {

    @Autowired
    private JardimRepository jardimRepository;

    public Jardim insert(Jardim jardim){
        jardim.setData(LocalDate.now());
        return jardimRepository.save(jardim);
    }

    public Jardim findLast(){
        return jardimRepository.findFirstByOrderByIdDesc();
    }

}

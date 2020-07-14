package com.osn.locadora.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osn.locadora.domain.Cidade;
import com.osn.locadora.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Long estadoId) {
		return repo.findCidades(estadoId);
	}

}

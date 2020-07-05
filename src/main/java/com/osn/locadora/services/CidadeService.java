package com.osn.locadora.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osn.locadora.domain.Cidade;
import com.osn.locadora.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public Cidade find(Long id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.osn.locadora.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

}

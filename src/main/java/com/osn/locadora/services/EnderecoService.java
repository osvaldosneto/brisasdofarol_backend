package com.osn.locadora.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.osn.locadora.domain.Endereco;
import com.osn.locadora.repository.EnderecoRepository;
import com.osn.locadora.services.exceptions.ObjectNotFoundException;

public class EnderecoService {
	
	@Autowired
	EnderecoRepository repo;
	
	public Endereco find(Long id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public void upDate(Endereco endereco) {
		
	}

}

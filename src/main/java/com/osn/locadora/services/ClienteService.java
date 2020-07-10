package com.osn.locadora.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Cidade;
import com.osn.locadora.domain.Cliente;
import com.osn.locadora.domain.Endereco;
import com.osn.locadora.dto.ClienteDTO;
import com.osn.locadora.dto.ClienteNewDTO;
import com.osn.locadora.repository.ClienteRepository;
import com.osn.locadora.repository.EnderecoRepository;
import com.osn.locadora.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private EnderecoRepository endRepo;

	public Cliente find(Long id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.osn.locadora.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente fromNewDTO(ClienteNewDTO objNewDTO) {
		Cidade cidade = cidadeService.find(objNewDTO.getCidadeId());	
		Endereco end = new Endereco(objNewDTO.getLogradouro(), objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(),
				objNewDTO.getCep(), cidade);	
		Cliente obj = new Cliente(objNewDTO.getNome(), objNewDTO.getEmail(), end);
		obj.getTelefones().add(objNewDTO.getTelefone1());
	
		if (objNewDTO.getTelefone2() != null) {
			obj.getTelefones().add(objNewDTO.getTelefone2());
		}
		if (objNewDTO.getTelefone3() != null) {
			obj.getTelefones().add(objNewDTO.getTelefone3());
		}
		
		end.setCliente(obj);
		
		return obj;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.save(obj.getEndereco());
		return obj;
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível exclir o cliente, verifique se existe dependencia em anexo.");
		}
	}

	public Cliente fromDTO(@Valid ClienteDTO objDTO) {
		Cliente cliente = new Cliente();
		cliente.setId(objDTO.getId());
		cliente.setNome(objDTO.getNome());
		cliente.setEmail(objDTO.getEmail());
		return cliente;
	}
	
	public Cliente fromDTO(@Valid ClienteNewDTO objNewDTO, Long id) {
		Set<String> telefones = new HashSet<>();
		Cidade cidade = cidadeService.find(objNewDTO.getCidadeId());
		Cliente cliente = find(id);
		
		Endereco endereco = cliente.getEndereco();
		endereco.setCidade(cidade);
		endereco.setBairro(objNewDTO.getBairro());
		endereco.setCep(objNewDTO.getCep());
		endereco.setComplemento(objNewDTO.getComplemento());
		endereco.setLogradouro(objNewDTO.getLogradouro());
		endereco.setNumero(objNewDTO.getNumero());
		
		cliente.setEndereco(endereco);
		cliente.setEmail(objNewDTO.getEmail());
		cliente.setNome(objNewDTO.getNome());
		
		telefones.add(objNewDTO.getTelefone1());
		if (objNewDTO.getTelefone2() != null) {
			telefones.add(objNewDTO.getTelefone2());
		}
		if (objNewDTO.getTelefone3() != null) {
			telefones.add(objNewDTO.getTelefone3());
		}
		cliente.setTelefones(telefones);
		
		return cliente;
	}

	public Cliente update(Cliente obj) {
		
		obj.setId(obj.getId());
		obj = repo.save(obj);
		endRepo.save(obj.getEndereco());
		return repo.save(obj);
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}

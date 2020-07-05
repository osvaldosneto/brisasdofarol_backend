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
		Cliente obj = new Cliente();
		Endereco end = new Endereco();
		end.setBairro(objNewDTO.getBairro());
		end.setCep(objNewDTO.getCep());
		end.setLogradouro(objNewDTO.getLogradouro());
		end.setNumero(objNewDTO.getNumero());
		end.setComplemento(objNewDTO.getComplemento());
		end.setCidade(cidade);
		obj.setNome(objNewDTO.getNome());
		obj.setEmail(objNewDTO.getEmail());
		obj.setEndereco(end);
		obj.getTelefones().add(objNewDTO.getTelefone1());

		if (objNewDTO.getTelefone2() != null) {
			obj.getTelefones().add(objNewDTO.getTelefone2());
		}
		if (objNewDTO.getTelefone3() != null) {
			obj.getTelefones().add(objNewDTO.getTelefone3());
		}

		return obj;
	}

	public Cliente fromNewDTOUpdate(ClienteNewDTO objNewDTO, Long id) {
		Set<String> telefones = new HashSet<>();
		Cliente obj = find(id);

		Cidade cidade = cidadeService.find(objNewDTO.getCidadeId());
		Endereco endereco = obj.getEndereco();
		endereco.setCidade(cidade);
		endereco.setLogradouro(objNewDTO.getNome());
		endereco.setBairro(objNewDTO.getBairro());
		endereco.setCep(objNewDTO.getCep());
		endereco.setNumero(objNewDTO.getNumero());
		endereco.setComplemento(objNewDTO.getComplemento());
		endereco.setCliente(obj);

		telefones.add(objNewDTO.getTelefone1());

		if (objNewDTO.getTelefone2() != null) {
			telefones.add(objNewDTO.getTelefone2());
		}
		if (objNewDTO.getTelefone3() != null) {
			telefones.add(objNewDTO.getTelefone2());
		}

		obj.setId(id);
		obj.setTelefones(telefones);
		obj.setNome(objNewDTO.getNome());
		obj.setEmail(objNewDTO.getEmail());
		obj.setEndereco(endereco);

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

	@Transactional
	public Cliente update(Cliente obj) {
		Cliente updateCliente = find(obj.getId());
		return repo.save(updateCliente);
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}

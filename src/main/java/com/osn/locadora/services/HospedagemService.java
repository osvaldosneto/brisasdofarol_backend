package com.osn.locadora.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Cidade;
import com.osn.locadora.domain.Endereco;
import com.osn.locadora.domain.Hospedagem;
import com.osn.locadora.dto.HospedagemDTO;
import com.osn.locadora.dto.HospedagemNewDTO;
import com.osn.locadora.repository.EnderecoRepository;
import com.osn.locadora.repository.HospedagemRepository;
import com.osn.locadora.services.exceptions.DataIntegrityException;

@Service
public class HospedagemService {

	@Autowired
	private HospedagemRepository repo;

	@Autowired
	private EnderecoRepository endRepo;

	@Autowired
	private CidadeService cidadeService;

	public Hospedagem find(Long id) {
		Optional<Hospedagem> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.osn.locadora.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Hospedagem.class.getName()));
	}

	public Hospedagem fromDTO(HospedagemDTO objDTO) {
		Hospedagem obj = new Hospedagem(objDTO.getNome(), objDTO.getMaximoHospedes(), objDTO.getValorDiaria(),
				objDTO.getValorHospedeExtra(), objDTO.getTaxaLimpeza(), null, null);
		obj.setId(objDTO.getId());
		return obj;
	}

	public List<Hospedagem> findAll() {
		return repo.findAll();
	}

	public Hospedagem update(Hospedagem obj) {
		Hospedagem updated = find(obj.getId());
		updated.setTaxaLimpeza(obj.getTaxaLimpeza());
		updated.setValorDiaria(obj.getValorDiaria());
		updated.setValorHospedeExtra(obj.getValorHospedeExtra());
		updated.setNome(obj.getNome());
		updated.setEndereco(obj.getEndereco());
		return repo.save(updated);
	}

	public Hospedagem fromNewDTO(HospedagemNewDTO objNewDTO) {

		Cidade cidade = cidadeService.find(objNewDTO.getCidadeId());
		Endereco end = new Endereco();
		end.setBairro(objNewDTO.getBairro());
		end.setCep(objNewDTO.getCep());
		end.setLogradouro(objNewDTO.getLogradouro());
		end.setNumero(objNewDTO.getNumero());
		end.setComplemento(objNewDTO.getComplemento());
		end.setCidade(cidade);

		Hospedagem obj = new Hospedagem(objNewDTO.getNome(), objNewDTO.getMaximoHospedes(), objNewDTO.getValorDiaria(),
				objNewDTO.getValorHospedeExtra(), objNewDTO.getTaxaLimpeza(), end, null);

		return obj;
	}

	@Transactional
	public Hospedagem insert(Hospedagem obj) {
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
	
	@Transactional
	public void atualizarLista(List<LocalDate> lista, Hospedagem hospedagem) {
		hospedagem.getListaDatas().removeAll(lista);
		repo.save(hospedagem);
	}
	
	

}

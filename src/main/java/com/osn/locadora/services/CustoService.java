package com.osn.locadora.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.osn.locadora.domain.Custos;
import com.osn.locadora.dto.CustoDTO;
import com.osn.locadora.repository.CustosRepository;
import com.osn.locadora.services.exceptions.DataIntegrityException;

@Service
public class CustoService {

	@Autowired
	private CustosRepository repo;

	public Custos find(Long id) {
		Optional<Custos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.osn.locadora.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Custos.class.getName()));
	}

	public List<Custos> findAll() {
		return repo.findAll();
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

	public Custos insert(Custos obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Custos fromDTO(CustoDTO objDTO) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Custos obj = new Custos(objDTO.getNome(), null, LocalDate.parse(objDTO.getDataPagamento(), formatador), objDTO.getValor(), objDTO.getDescricao());
		return obj;
	}

	public Custos update(Custos obj) {
		obj.setId(obj.getId());
		return repo.save(obj);
	}

	public List<Custos> findByDateAndName(String dia1, String dia2, String nome) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(dia1, formatador);
		LocalDate date2 = LocalDate.parse(dia2, formatador);
		if("".equals(nome)) {
			return repo.findByDate(date1, date2);
		}else {
			return repo.findByDateAndName(date1, date2, nome);
		}	
	}

}

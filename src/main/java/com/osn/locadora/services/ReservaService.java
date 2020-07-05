package com.osn.locadora.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Cliente;
import com.osn.locadora.domain.Hospedagem;
import com.osn.locadora.domain.Reserva;
import com.osn.locadora.domain.enums.TipoIntermedio;
import com.osn.locadora.dto.ClienteNewDTO;
import com.osn.locadora.dto.ReservaDTO;
import com.osn.locadora.dto.ReservaNewDTO;
import com.osn.locadora.repository.ReservaRepository;
import com.osn.locadora.services.exceptions.DataIntegrityException;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository repo;

	@Autowired
	private HospedagemService hospedagemService;

	@Autowired
	private ClienteService clienteService;

	public Reserva find(Long id) {
		Optional<Reserva> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.osn.locadora.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Reserva.class.getName()));
	}

	public ReservaDTO findId(Long id) {
		ReservaDTO objDTO = new ReservaDTO(find(id));
		return objDTO;
	}

	public List<Reserva> findAll() {
		return repo.findAll();
	}

	public Reserva fromNewDTO(ReservaNewDTO objNewDTO) {
		Hospedagem hospedagem = hospedagemService.find(objNewDTO.getIdHospedagem());

		ClienteNewDTO clienteNewDTO = new ClienteNewDTO(null, objNewDTO.getNome(), objNewDTO.getEmail(),
				objNewDTO.getTelefone1(), objNewDTO.getTelefone3(), objNewDTO.getTelefone2(), objNewDTO.getLogradouro(),
				objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep(),
				objNewDTO.getIdCidade());
		Cliente cliente = clienteService.fromNewDTO(clienteNewDTO);

		Reserva obj = new Reserva(objNewDTO.getCheckIn(), objNewDTO.getCheckOut(), objNewDTO.getNumeroHospedes(),
				TipoIntermedio.toEnum(objNewDTO.getTipoIntermedio()), objNewDTO.getDesconto());

		obj.setCliente(cliente);
		obj.setHospedagem(hospedagem);
		hospedagem.getReservas().add(obj);
		cliente.getReservas().add(obj);

		return obj;

	}

	@Transactional
	public Reserva insert(Reserva obj) {
		obj.setId(null);
		obj = repo.save(obj);
		clienteService.insert(obj.getCliente());
		return obj;
	}

	public Reserva fromDTO(@Valid ReservaDTO objDTO) {
		Cliente cliente = clienteService.find(objDTO.getIdCliente());
		Hospedagem hospedagem = hospedagemService.find(objDTO.getIdHospedagem());
		Reserva obj = new Reserva( objDTO.getCheckIn(), objDTO.getCheckOut(), objDTO.getNumeroHospedes(),
				TipoIntermedio.toEnum(objDTO.getTipoIntermedio()), objDTO.getDesconto());
		obj.setHospedagem(hospedagem);
		hospedagem.getReservas().add(obj);
		obj.setCliente(cliente);
		cliente.getReservas().add(obj);
		return obj;
	}
	
	@Transactional
	public Reserva insertNova(Reserva obj) {
		obj.setId(null);
		obj = repo.save(obj);
		clienteService.update(obj.getCliente());
		hospedagemService.update(obj.getHospedagem());
		return obj;
	}
	
	public Page<Reserva> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
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

}

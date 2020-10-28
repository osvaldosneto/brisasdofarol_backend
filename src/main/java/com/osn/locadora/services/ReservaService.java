package com.osn.locadora.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import com.osn.locadora.domain.enums.TipoLimpeza;
import com.osn.locadora.dto.ClienteNewDTO;
import com.osn.locadora.dto.ReservaDTO;
import com.osn.locadora.dto.ReservaNewDTO;
import com.osn.locadora.dto.ReservaUpdateDTO;
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
	
	@Autowired
	private EmailService emailService;

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
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Hospedagem hospedagem = hospedagemService.find(objNewDTO.getIdHospedagem());	
		for (LocalDate ld = LocalDate.parse(objNewDTO.getCheckIn(), formatador);
			ld.isBefore(LocalDate.parse(objNewDTO.getCheckOut(), formatador)); ld = ld.plusDays(1)) {
			hospedagem.getListaDatas().add(ld);
		}
		ClienteNewDTO clienteNewDTO = new ClienteNewDTO(null, objNewDTO.getNome(), objNewDTO.getEmail(),
				objNewDTO.getTelefone1(), objNewDTO.getTelefone3(), objNewDTO.getTelefone2(), objNewDTO.getLogradouro(),
				objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep(),
				objNewDTO.getIdCidade());
		Cliente cliente = clienteService.fromNewDTO(clienteNewDTO);
		Reserva obj = new Reserva(LocalDate.parse(objNewDTO.getCheckIn(), formatador),
				LocalDate.parse(objNewDTO.getCheckOut(), formatador), objNewDTO.getNumeroHospedes(),
				TipoIntermedio.toEnum(objNewDTO.getTipoIntermedio()), objNewDTO.getDesconto(),
				TipoLimpeza.toEnum(objNewDTO.getTipoLimpeza()));
		obj.setCliente(cliente);
		obj.setHospedagem(hospedagem);
		obj.setTotal(this.calculoTotal(obj));
		hospedagem.getReservas().add(obj);
		cliente.getReservas().add(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

	public Reserva fromNewDTOUpdate(@Valid ReservaUpdateDTO objUpdateDTO, Long id) {
		Reserva obj = find(id);
		
		Hospedagem hospedagem = obj.getHospedagem();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (LocalDate ld = LocalDate.parse(objUpdateDTO.getCheckIn(), formatador); ld
				.isBefore(LocalDate.parse(objUpdateDTO.getCheckOut(), formatador)); ld = ld.plusDays(1)) {
			hospedagem.getListaDatas().add(ld);
		}
		obj.setHospedagem(hospedagem);
		obj.setCheckIn(LocalDate.parse(objUpdateDTO.getCheckIn(), formatador));
		obj.setCheckOut(LocalDate.parse(objUpdateDTO.getCheckOut(), formatador));
		obj.setDesconto(objUpdateDTO.getDesconto());
		obj.setNow(LocalDate.now());
		obj.setNumeroHospedes(objUpdateDTO.getNumeroHospedes());
		obj.setTipoIntermedio(TipoIntermedio.toEnum(objUpdateDTO.getTipoIntermedio()));
		obj.setTipoLimpeza(TipoLimpeza.toEnum(objUpdateDTO.getTipoLimpeza()));
		obj.setTotal(this.calculoTotal(obj));
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

	public Reserva fromDTO(@Valid ReservaDTO objDTO) {	
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Cliente cliente = clienteService.find(objDTO.getIdCliente());
		Hospedagem hospedagem = hospedagemService.find(objDTO.getIdHospedagem());
		for (LocalDate ld = LocalDate.parse(objDTO.getCheckIn(), formatador); ld
				.isBefore(LocalDate.parse(objDTO.getCheckOut(), formatador)); ld = ld.plusDays(1)) {
			hospedagem.getListaDatas().add(ld);
		}
		Reserva obj = new Reserva(LocalDate.parse(objDTO.getCheckIn(), formatador),
				LocalDate.parse(objDTO.getCheckOut(), formatador), objDTO.getNumeroHospedes(),
				TipoIntermedio.toEnum(objDTO.getTipoIntermedio()), objDTO.getDesconto(),
				TipoLimpeza.toEnum(objDTO.getTipoLimpeza()));
		obj.setHospedagem(hospedagem);
		obj.setTotal(this.calculoTotal(obj));
		hospedagem.getReservas().add(obj);
		obj.setCliente(cliente);
		cliente.getReservas().add(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

	private double calculoTotal(Reserva reserva) {
		long diasHospedado = ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
		double total;
		if (reserva.getNumeroHospedes() > 2) {
			total = (reserva.getHospedagem().getValorDiaria()
					+ (reserva.getHospedagem().getValorHospedeExtra() * (reserva.getNumeroHospedes() - 2)))
					* diasHospedado;
		} else {
			total = (reserva.getHospedagem().getValorDiaria() * diasHospedado);
		}
		if (reserva.getTipoLimpeza().getCod() == 1) {
			total += reserva.getHospedagem().getTaxaLimpeza();
		}
		return total - reserva.getDesconto();
	}

	public Page<Reserva> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public void delete(Long id) {
		find(id);
		try {
			Reserva obj = this.find(id);
			
			for (LocalDate ld = obj.getCheckIn(); ld.isBefore(obj.getCheckOut()); ld = ld.plusDays(1)) {
				obj.getHospedagem().getListaDatas().remove(ld);
			}
			hospedagemService.update(obj.getHospedagem());
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível exclir a reserva, verifique se existe dependencia em anexo.");
		}
	}

	@Transactional
	public Reserva insertNova(Reserva obj) {
		obj.setId(null);
		obj = repo.save(obj);
		clienteService.update(obj.getCliente());
		hospedagemService.update(obj.getHospedagem());
		return obj;
	}

	@Transactional
	public Reserva update(Reserva obj) {
		Reserva objUpdate = find(obj.getId());
		objUpdate.setCheckIn(obj.getCheckIn());
		objUpdate.setCheckOut(obj.getCheckOut());
		objUpdate.setCliente(obj.getCliente());
		objUpdate.setDesconto(obj.getDesconto());
		objUpdate.setHospedagem(obj.getHospedagem());
		objUpdate.setId(obj.getId());
		objUpdate.setNow(LocalDate.now());
		objUpdate.setNumeroHospedes(obj.getNumeroHospedes());
		objUpdate.setTipoIntermedio(obj.getTipoIntermedio());
		objUpdate.setTipoLimpeza(obj.getTipoLimpeza());
		objUpdate.setTotal(this.calculoTotal(obj));	
		return repo.save(objUpdate);
	}

	@Transactional
	public Reserva insert(Reserva obj) {
		obj.setId(null);
		obj = repo.save(obj);
		clienteService.insert(obj.getCliente());
		return obj;
	}
	
	public void updateHospedagensDiarias(Long id) {
		Reserva obj = find(id);
		LocalDate checkIn = obj.getCheckIn();
		LocalDate checkOut = obj.getCheckOut();
		List<LocalDate> listaRemover = new ArrayList<>();
		for (LocalDate ld = checkIn; ld.isBefore(checkOut); ld = ld.plusDays(1)) {
			listaRemover.add(ld);
		}
		hospedagemService.atualizarLista(listaRemover, obj.getHospedagem());
	}
	
	public List<Reserva> findByDate(String date) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate data = LocalDate.parse(date, formatador);
		return repo.findByDate(data);
	}

	public List<Reserva> findByHospedagemCliente(String idHospedagem, String idCliente) {	
		if(!"%".equals(idHospedagem) && !"%".equals(idCliente)) {
			return repo.findByHospedagemCliente(Long.parseLong(idCliente), Long.parseLong(idHospedagem));
		} else if("%".equals(idHospedagem) && "%".equals(idCliente)){
			return this.findAll();
		} else if("%".equals(idHospedagem) && !"%".equals(idCliente)){
			return repo.findBycliente(Long.parseLong(idCliente));
		} else {
			return repo.findByhospedagem(Long.parseLong(idHospedagem));
		}
		
	}
	
}

package com.osn.locadora.resources;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.osn.locadora.domain.Reserva;
import com.osn.locadora.dto.ReservaDTO;
import com.osn.locadora.dto.ReservaNewDTO;
import com.osn.locadora.dto.ReservaUpdateDTO;
import com.osn.locadora.repository.ReservaRepository;
import com.osn.locadora.services.ReservaService;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaResource {

	@Autowired
	private ReservaService service;

	@Autowired
	private ReservaRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ReservaDTO>> findAll() {
		List<Reserva> listaCli = service.findAll();
		List<ReservaDTO> listaDTO = new ArrayList<ReservaDTO>();
		for (Reserva r : listaCli) {
			ReservaDTO cliDTO = new ReservaDTO(r);
			listaDTO.add(cliDTO);
		}
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> findAllReservas() {
		List<Reserva> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		ReservaDTO objDTO = service.findId(id);
		return ResponseEntity.ok().body(objDTO);
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ReservaNewDTO objDTO) {
		Reserva obj = service.fromNewDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertNova(@Valid @RequestBody ReservaDTO objDTO) {
		Reserva obj = service.fromDTO(objDTO);
		obj = service.insertNova(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ReservaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "now") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Reserva> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ReservaDTO> listDto = list.map(obj -> new ReservaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/entredatas", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> filtro(@RequestParam(value = "dia1", defaultValue = "2019-01-01") String dia1,
			@RequestParam(value = "dia2", defaultValue = "2050-12-31") String dia2) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(dia1, formatador);
		LocalDate date2 = LocalDate.parse(dia2, formatador);
		System.out.println("entre datas " + dia1);
		System.out.println("entre datas " + dia2);
		List<Reserva> lista = repo.findByCreatedDateBetween(date1, date2);
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> edit(@PathVariable Long id) {
		service.updateHospedagensDiarias(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ReservaUpdateDTO objUpdateDTO, @PathVariable Long id) {
		System.out.println("Id resource : " + id);
		Reserva obj = service.fromNewDTOUpdate(objUpdateDTO, id);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/filtrodata", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> filtroReserva(@RequestParam(value = "data", defaultValue = "") String data) {
		List<Reserva> lista = service.findByDate(data);	
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/filtrohospedagem", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> filtroReservaHos(@RequestParam(value = "idHospedagem", defaultValue = "%") String idHospedagem,
			@RequestParam(value = "idCliente", defaultValue = "%") String idCliente) {
		List<Reserva> lista = service.findByHospedagemCliente(idHospedagem, idCliente);	
		return ResponseEntity.ok().body(lista);
	}
	
}

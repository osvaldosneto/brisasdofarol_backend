package com.osn.locadora.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.osn.locadora.domain.Hospedagem;
import com.osn.locadora.dto.HospedagemDTO;
import com.osn.locadora.dto.HospedagemNewDTO;
import com.osn.locadora.services.HospedagemService;

@RestController
@RequestMapping(value = "/hospedagens")
public class HospedagemResource {

	@Autowired
	private HospedagemService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HospedagemDTO>> findAll() {
		List<Hospedagem> lista = service.findAll();
		List<HospedagemDTO> listaDTO = new ArrayList<>();
		for (Hospedagem h : lista) {
			HospedagemDTO objDTO = new HospedagemDTO(h);
			listaDTO.add(objDTO);
		}
		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Hospedagem obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody HospedagemNewDTO objDTO, @PathVariable Long id) {
		Hospedagem obj = service.fromNewDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody HospedagemNewDTO objDTO) {
		Hospedagem obj = service.fromNewDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

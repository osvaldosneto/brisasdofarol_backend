package com.osn.locadora.services.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.osn.locadora.domain.Hospedagem;
import com.osn.locadora.dto.ReservaDTO;
import com.osn.locadora.resources.exceptions.FieldMessage;
import com.osn.locadora.services.HospedagemService;

public class ReservaNewInsertValidator implements ConstraintValidator<ReservaNewInsert, ReservaDTO> {

	@Autowired
	private HospedagemService hospService;

	@Override
	public void initialize(ReservaNewInsert ann) {
	}

	@Override
	public boolean isValid(ReservaDTO objDto, ConstraintValidatorContext context) {

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate checkIn = LocalDate.parse(objDto.getCheckIn(), formatador);
		LocalDate checkOut = LocalDate.parse(objDto.getCheckOut(), formatador);
		
		List<FieldMessage> lista = new ArrayList<>();

		Hospedagem hospedagem = hospService.find(objDto.getIdHospedagem());
		List<LocalDate> datasReservadas = hospedagem.getListaDatas();
		
		if(checkOut.isBefore(checkIn)) {
			lista.add(new FieldMessage("checkIn", "Verifique a data de entrada e saída."));
		}
		
		if(datasReservadas.contains(checkIn)) {
			lista.add(new FieldMessage("checkIn", "Verifique a data de entrada, hospedagem já reservada."));
		}
		
		for (LocalDate ld = checkIn; ld.isBefore(checkOut); ld = ld.plusDays(1)) {
			if(datasReservadas.contains(ld)) {
				lista.add(new FieldMessage("checkIn", "Verifique a data de entrada, hospedagem já reservada."));
			}
		}

		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}

}

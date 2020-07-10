package com.osn.locadora.dto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.validation.constraints.NotNull;

import com.osn.locadora.domain.Reserva;
import com.osn.locadora.services.validation.ReservaNewInsert;

@ReservaNewInsert
public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Preenchimento obrigatório")
	private String checkIn;
	@NotNull(message = "Preenchimento obrigatório")
	private String checkOut;
	@NotNull(message = "Preenchimento obrigatório")
	private Integer numeroHospedes;
	@NotNull(message = "Preenchimento obrigatório")
	private Integer tipoIntermedio;
	@NotNull(message = "Preenchimento obrigatório")
	private double desconto;

	private String cliente;
	@NotNull(message = "Preenchimento obrigatório")
	private Long idCliente;

	private String hospedagem;
	@NotNull(message = "Preenchimento obrigatório")
	private Long idHospedagem;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Integer tipoLimpeza;

	private double total;

	public ReservaDTO() {

	}

	public ReservaDTO(Reserva reserva) {
		this.id = reserva.getId();
		this.checkIn = reserva.getCheckIn().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		this.checkOut = reserva.getCheckOut().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		this.numeroHospedes = reserva.getNumeroHospedes();
		this.tipoIntermedio = reserva.getTipoIntermedio().getCod();
		this.desconto = reserva.getDesconto();
		this.cliente = reserva.getCliente().getNome();
		this.idCliente = reserva.getCliente().getId();
		this.hospedagem = reserva.getHospedagem().getNome();
		this.idHospedagem = reserva.getHospedagem().getId();
		this.tipoLimpeza = reserva.getTipoLimpeza().getCod();
	}
	
	public ReservaDTO(ReservaUpdateDTO reserva) {
		this.id = reserva.getId();
		this.checkIn = reserva.getCheckIn();
		this.checkOut = reserva.getCheckOut();
		this.numeroHospedes = reserva.getNumeroHospedes();
		this.tipoIntermedio = reserva.getTipoIntermedio();
		this.desconto = reserva.getDesconto();
		this.idCliente = reserva.getClienteId();
		this.idHospedagem = reserva.getHospedagemId();
		this.tipoLimpeza = reserva.getTipoLimpeza();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public Integer getNumeroHospedes() {
		return numeroHospedes;
	}

	public void setNumeroHospedes(Integer numeroHospedes) {
		this.numeroHospedes = numeroHospedes;
	}

	public Integer getTipoIntermedio() {
		return tipoIntermedio;
	}

	public void setTipoIntermedio(Integer tipoIntermedio) {
		this.tipoIntermedio = tipoIntermedio;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(String hospedagem) {
		this.hospedagem = hospedagem;
	}

	public Long getIdHospedagem() {
		return idHospedagem;
	}

	public void setIdHospedagem(Long idHospedagem) {
		this.idHospedagem = idHospedagem;
	}

	public Integer getTipoLimpeza() {
		return tipoLimpeza;
	}

	public void setTipoLimpeza(Integer tipoLimpeza) {
		this.tipoLimpeza = tipoLimpeza;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}

package com.osn.locadora.dto;

import javax.validation.constraints.NotNull;

import com.osn.locadora.services.validation.ReservaUpdate;

@ReservaUpdate
public class ReservaUpdateDTO {

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
	@NotNull(message = "Preenchimento obrigatório")
	private Integer tipoLimpeza;
	@NotNull(message = "Preenchimento obrigatório")
	private Long hospedagemId;
	@NotNull(message = "Preenchimento obrigatório")
	private Long clienteId;

	public ReservaUpdateDTO() {

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

	public Integer getTipoLimpeza() {
		return tipoLimpeza;
	}

	public void setTipoLimpeza(Integer tipoLimpeza) {
		this.tipoLimpeza = tipoLimpeza;
	}

	public Long getHospedagemId() {
		return hospedagemId;
	}

	public void setHospedagemId(Long hospedagemId) {
		this.hospedagemId = hospedagemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

}

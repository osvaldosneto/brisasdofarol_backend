package com.osn.locadora.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.validation.constraints.NotEmpty;

import com.osn.locadora.domain.Reserva;

public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	private LocalDate now;
	@NotEmpty(message="Preenchimento obrigatório")
	private String checkIn;
	@NotEmpty(message="Preenchimento obrigatório")
	private String checkOut;
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer numeroHospedes;
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer tipoIntermedio;
	@NotEmpty(message="Preenchimento obrigatório")
	private double desconto;
	@NotEmpty(message="Preenchimento obrigatório")
	private double total;
	@NotEmpty(message="Preenchimento obrigatório")
	private String cliente;
	@NotEmpty(message="Preenchimento obrigatório")
	private Long idCliente;
	private String hospedagem;
	@NotEmpty(message="Preenchimento obrigatório")
	private Long idHospedagem;
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer tipoLimpeza;

	public ReservaDTO() {

	}

	public ReservaDTO(Reserva reserva) {
		this.id = reserva.getId();
		this.now = reserva.getNow();
		this.checkIn = reserva.getCheckIn().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		this.checkOut = reserva.getCheckOut().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		this.numeroHospedes = reserva.getNumeroHospedes();
		this.tipoIntermedio = reserva.getTipoIntermedio().getCod();
		this.desconto = reserva.getDesconto();
		this.cliente = reserva.getCliente().getNome();
		this.idCliente = reserva.getCliente().getId();
		this.hospedagem = reserva.getHospedagem().getNome();
		this.idHospedagem = reserva.getHospedagem().getId();
		this.total = reserva.getTotal();
		this.tipoLimpeza = reserva.getTipoLimpeza().getCod();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getNow() {
		return now;
	}

	public void setNow(LocalDate now) {
		this.now = now;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

}

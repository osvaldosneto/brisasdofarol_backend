package com.osn.locadora.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.osn.locadora.domain.Reserva;

public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate now;
	private Date checkIn;
	private Date checkOut;
	private Integer numeroHospedes;
	private Integer tipoIntermedio;
	private double desconto;
	private double total;
	private String cliente;
	private Long idCliente;
	private String hospedagem;
	private Long idHospedagem;

	public ReservaDTO() {

	}

	public ReservaDTO(Reserva reserva) {
		this.id = reserva.getId();
		this.now = reserva.getNow();
		this.checkIn = reserva.getCheckIn();
		this.checkOut = reserva.getCheckOut();
		this.numeroHospedes = reserva.getNumeroHospedes();
		this.tipoIntermedio = reserva.getTipoIntermedio().getCod();
		this.desconto = reserva.getDesconto();
		this.total = reserva.getTotal();
		this.cliente = reserva.getCliente().getNome();
		this.idCliente = reserva.getCliente().getId();
		this.hospedagem = reserva.getHospedagem().getNome();
		this.idHospedagem = reserva.getHospedagem().getId();
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

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
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

}

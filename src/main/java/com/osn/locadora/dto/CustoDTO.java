package com.osn.locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.osn.locadora.domain.Custos;

public class CustoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Preenchimento obrigatório")
	private String nome;
	private String now;
	@NotNull(message = "Preenchimento obrigatório")
	private String dataPagamento;
	@NotNull
	private double valor;

	public CustoDTO() {

	}

	public CustoDTO(Custos obj) {
		this.nome = obj.getNome();
		this.now = obj.getNow().toString();
		this.valor = obj.getValor();
		this.dataPagamento = obj.getDataPagamento().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}

package com.osn.locadora.dto;

import java.io.Serializable;

public class HospedagemNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer maximoHospedes;
	private double valorDiaria;
	private double valorHospedeExtra;
	private double taxaLimpeza;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	private Long cidadeId;

	private Long estadoId;

	public HospedagemNewDTO() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMaximoHospedes() {
		return maximoHospedes;
	}

	public void setMaximoHospedes(Integer maximoHospedes) {
		this.maximoHospedes = maximoHospedes;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public double getValorHospedeExtra() {
		return valorHospedeExtra;
	}

	public void setValorHospedeExtra(double valorHospedeExtra) {
		this.valorHospedeExtra = valorHospedeExtra;
	}

	public double getTaxaLimpeza() {
		return taxaLimpeza;
	}

	public void setTaxaLimpeza(double taxaLimpeza) {
		this.taxaLimpeza = taxaLimpeza;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidade_id) {
		this.cidadeId = cidade_id;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estado_id) {
		this.estadoId = estado_id;
	}

}

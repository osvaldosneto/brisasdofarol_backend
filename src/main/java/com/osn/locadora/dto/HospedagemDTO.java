package com.osn.locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.osn.locadora.domain.Hospedagem;

public class HospedagemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=120, message="O tamanho deve ser entre 3 e 120 caracteres")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer maximoHospedes;
	@NotEmpty(message="Preenchimento obrigatório")
	private double valorDiaria;
	@NotEmpty(message="Preenchimento obrigatório")
	private double valorHospedeExtra;
	@NotEmpty(message="Preenchimento obrigatório")
	private double taxaLimpeza;

	public HospedagemDTO() {

	}

	public HospedagemDTO(Hospedagem obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.maximoHospedes = obj.getMaximoHospedes();
		this.valorDiaria = obj.getValorDiaria();
		this.valorHospedeExtra = obj.getValorHospedeExtra();
		this.taxaLimpeza = obj.getTaxaLimpeza();
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

}

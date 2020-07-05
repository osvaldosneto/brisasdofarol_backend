package com.osn.locadora.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Hospedagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer maximoHospedes;
	private double valorDiaria;
	private double valorHospedeExtra;
	private double taxaLimpeza;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToMany(mappedBy = "hospedagem", cascade = CascadeType.ALL)
	private List<Reserva> reservas = new ArrayList<>();

	public Hospedagem() {

	}

	public Hospedagem(String nome, Integer maximoHospedes, double valorDiaria, double valorHospedeExtra,
			double taxaLimpeza, Endereco endereco, Reserva reserva) {
		super();
		this.nome = nome;
		this.maximoHospedes = maximoHospedes;
		this.valorDiaria = valorDiaria;
		this.valorHospedeExtra = valorHospedeExtra;
		this.taxaLimpeza = taxaLimpeza;
		this.endereco = endereco;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospedagem other = (Hospedagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}

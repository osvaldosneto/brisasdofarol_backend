package com.osn.locadora.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class ReservaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	private String email;
	private String telefone1;
	private String telefone3;
	private String telefone2;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Long idCidade;
	private Long idHospedagem;

	private LocalDate now;
	private Date checkIn;
	private Date checkOut;
	private Integer numeroHospedes;
	private Integer tipoIntermedio;
	private double desconto;
	private double total;

	public ReservaNewDTO() {
		this.now = LocalDate.now();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
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

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public Long getIdHospedagem() {
		return idHospedagem;
	}

	public void setIdHospedagem(Long idHospedagem) {
		this.idHospedagem = idHospedagem;
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

}

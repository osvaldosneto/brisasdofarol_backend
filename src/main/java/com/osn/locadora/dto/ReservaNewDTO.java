package com.osn.locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.osn.locadora.services.validation.ReservaInsert;

@ReservaInsert
public class ReservaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotNull(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	@NotNull(message="Preenchimento obrigatório")
	private String telefone1;
	private String telefone3;
	private String telefone2;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	@NotNull(message="Preenchimento obrigatório")
	private Long idCidade;
	@NotNull(message="Preenchimento obrigatório")
	private Long idHospedagem;
	
	@NotNull(message="Preenchimento obrigatório")
	private String checkIn;
	@NotNull(message="Preenchimento obrigatório")
	private String checkOut;
	@NotNull(message="Preenchimento obrigatório")
	private Integer numeroHospedes;
	@NotNull(message="Preenchimento obrigatório")
	private Integer tipoIntermedio;
	@NotNull(message="Preenchimento obrigatório")
	private double desconto;
	@NotNull(message="Preenchimento obrigatório")
	private Integer tipoLimpeza;

	public ReservaNewDTO() {

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

}

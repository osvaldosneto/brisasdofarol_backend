package com.osn.locadora.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.osn.locadora.domain.enums.TipoIntermedio;
import com.osn.locadora.domain.enums.TipoLimpeza;

@Entity
@Table(name = "tb_reservas")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate now;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Integer numeroHospedes;
	private Integer tipoIntermedio;
	private double desconto;
	private double total;
	private Integer tipoLimpeza;

	@ManyToOne
	@JoinColumn(name = "hospedagens_id")
	private Hospedagem hospedagem;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Reserva() {
		this.now = LocalDate.now();
	}

	public Reserva(LocalDate checkIn, LocalDate checkOut, Integer numeroHospedes, TipoIntermedio tipoIntermedio,
			double desconto, TipoLimpeza tipoLimpeza) {
		super();
		this.now = LocalDate.now();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numeroHospedes = numeroHospedes;
		this.tipoIntermedio = tipoIntermedio.getCod();
		this.desconto = desconto;
		this.tipoLimpeza = tipoLimpeza.getCod();

	}

	public LocalDate getNow() {
		return now;
	}

	public void setNow(LocalDate now) {
		this.now = now;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public Integer getNumeroHospedes() {
		return numeroHospedes;
	}

	public void setNumeroHospedes(Integer numeroHospedes) {
		this.numeroHospedes = numeroHospedes;
	}

	public TipoIntermedio getTipoIntermedio() {
		return TipoIntermedio.toEnum(tipoIntermedio);
	}

	public void setTipoIntermedio(TipoIntermedio tipoIntermedio) {
		this.tipoIntermedio = tipoIntermedio.getCod();
	}

	public TipoLimpeza getTipoLimpeza() {
		return TipoLimpeza.toEnum(tipoLimpeza);
	}

	public void setTipoLimpeza(TipoLimpeza tipoLimpeza) {
		this.tipoLimpeza = tipoLimpeza.getCod();
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

	public Hospedagem getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(Hospedagem hospedagem) {
		this.hospedagem = hospedagem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Reserva other = (Reserva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		builder.append("Olá " + cliente.getNome() + "!!! \n");
		builder.append("\n");
		builder.append("Aqui é o Osvaldo e a Rafa, anfitriões das casinhas brisas do farol, "
				+ "esta é uma mensagem automática de confirmação da sua reserva.");
		builder.append("\n");
		builder.append("\n");
		// local
		if (tipoIntermedio == 0) {

			builder.append("Hospedagem : " + hospedagem.getNome());
			builder.append("\n");
			builder.append("Data CheckIn : " + checkIn.format(formatter));
			builder.append("\n");
			builder.append("Data CheckOut : " + checkOut.format(formatter));
			builder.append("\n");
			builder.append("Número de Hóspedes : " + numeroHospedes);
			builder.append("\n");
			builder.append("Subtotal : " + nf.format(total + desconto));
			builder.append("\n");
			builder.append("Desconto : " + nf.format(desconto));
			builder.append("\n");
			builder.append("Total : " + nf.format(total));
			builder.append("\n");

		} else {

			builder.append("Hospedagem :" + hospedagem.getNome());
			builder.append("\n");
			builder.append("Data CheckIn : " + checkIn.format(formatter));
			builder.append("\n");
			builder.append("Data CheckOut : " + checkOut.format(formatter));
			builder.append("\n");
			builder.append("Número de Hóspedes :" + numeroHospedes);
			builder.append("\n");
		}
		builder.append("\n");
		builder.append(
				"Qualquer dúvida  estamos à disposição pelo email ou telefones (48) 99175 5501 / (48) 991525502.");

		return builder.toString();
	}

}

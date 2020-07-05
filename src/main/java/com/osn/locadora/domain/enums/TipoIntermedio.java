package com.osn.locadora.domain.enums;

public enum TipoIntermedio {

	AIRBNB(1, "Airbnb"), LOCAL(2, "Local");

	private int cod;
	private String descricao;

	private TipoIntermedio(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoIntermedio toEnum(Integer tipoIntermedio) {
		if (tipoIntermedio == null) {
			return null;
		}
		for (TipoIntermedio tipo : TipoIntermedio.values()) {
			if (tipoIntermedio.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido :" + tipoIntermedio);

	}

}

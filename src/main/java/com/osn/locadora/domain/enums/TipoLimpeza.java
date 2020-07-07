package com.osn.locadora.domain.enums;

public enum TipoLimpeza {

	SIM(1, "Com_Taxa"), NAO(0, "Sem_Taxa");

	private int cod;
	private String descricao;

	private TipoLimpeza(int cod, String descricao) {
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

	public static TipoLimpeza toEnum(Integer tipoIntermedio) {
		if (tipoIntermedio == null) {
			return null;
		}
		for (TipoLimpeza tipo : TipoLimpeza.values()) {
			if (tipoIntermedio.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido :" + tipoIntermedio);

	}

}

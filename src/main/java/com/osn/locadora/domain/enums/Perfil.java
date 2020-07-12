package com.osn.locadora.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(0, "ROLE_CLIENTE");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
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

	public static Perfil toEnum(Integer tipoIntermedio) {
		if (tipoIntermedio == null) {
			return null;
		}
		for (Perfil tipo : Perfil.values()) {
			if (tipoIntermedio.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido :" + tipoIntermedio);

	}

}

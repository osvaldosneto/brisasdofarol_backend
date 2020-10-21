package com.osn.locadora.domain.enums;

public enum Status {

    INATIVO(1, "INATIVO"), ATIVO(0, "ATIVO");

    private int cod;
    private String descricao;

    private Status(int cod, String descricao) {
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

    public static Status toEnum(Integer tipoStatus) {
        if (tipoStatus == null) {
            return null;
        }
        for (Status tipo : Status.values()) {
            if (tipoStatus.equals(tipo.getCod())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id inválido :" + tipoStatus);
    }
}

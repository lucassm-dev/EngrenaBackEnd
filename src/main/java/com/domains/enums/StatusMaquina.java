package com.domains.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusMaquina {
    OPERANDO(0, "OPERANDO"), EM_MANUTENCAO(1, "EM_MANUTENCAO"), PARADA(2, "PARADA"), DESATIVADA(3, "DESATIVADA");

    private Integer id;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static StatusMaquina toEnum(Integer id){
        if(id == null) return null;
        for(StatusMaquina statusMaquina : StatusMaquina.values()){
            if(id.equals(statusMaquina.getId())){
                return statusMaquina;
            }
        }
        throw new IllegalArgumentException("Status da Máquina Inválida!");
    }
}

package com.domains.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusOrdemServico {
    ABERTA(0, "ABERTA"), EM_ANDAMENTO(1, "EM_ANDAMENTO"), AGUARDANDO_PECA(2, "AGUARDANDO_PECA"), FINALIZADA(3, "FINALIZADA"), CANCELADA(4, "CANCELADA");

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

    public static StatusOrdemServico toEnum(Integer id){
        if(id == null) return null;
        for(StatusOrdemServico statusOS : StatusOrdemServico.values()){
            if(id.equals(statusOS.getId())){
                return statusOS;
            }
        }
        throw new IllegalArgumentException("Status de Ordem de Serviço Inválido!");
    }
}

package com.domains.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoManutencao {
    PREVENTIVA(0, "PREVENTIVA"), CORRETIVA(1, "CORRETIVA"), PREDITIVA(2, "PREDITIVA");

    private Integer id;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static TipoManutencao toEnum(Integer id){
        if(id == null) return null;
        for(TipoManutencao tipoManutencao : TipoManutencao.values()){
            if(id.equals(tipoManutencao.getId())){
                return tipoManutencao;
            }
        }
        throw new IllegalArgumentException("Tipo de Manutenção inválida!");
    }
}

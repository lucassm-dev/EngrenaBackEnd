package com.infra;

import com.domains.enums.TipoManutencao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoManutencaoConverter implements AttributeConverter<TipoManutencao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoManutencao tipoManutencao) {
        return tipoManutencao == null ? null : tipoManutencao.getId();
    }

    @Override
    public TipoManutencao convertToEntityAttribute(Integer dbValue) {
        return TipoManutencao.toEnum(dbValue);
    }
}

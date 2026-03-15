package com.infra;

import com.domains.enums.StatusOrdemServico;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusOrdemServicoConverter implements AttributeConverter<StatusOrdemServico, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusOrdemServico status) {
        return status == null ? null : status.getId();
    }

    @Override
    public StatusOrdemServico convertToEntityAttribute(Integer dbValue) {
        return StatusOrdemServico.toEnum(dbValue);
    }
}

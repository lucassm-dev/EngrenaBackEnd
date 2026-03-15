package com.infra;

import com.domains.enums.StatusMaquina;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusMaquinaConverter implements AttributeConverter<StatusMaquina, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusMaquina statusMaquina) {
        return statusMaquina == null ? null : statusMaquina.getId();
    }

    @Override
    public StatusMaquina convertToEntityAttribute(Integer dbValue) {
        return StatusMaquina.toEnum(dbValue);
    }
}

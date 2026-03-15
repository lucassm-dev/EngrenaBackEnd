package com.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PecaUtilizadaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    private String nomePeca;

    @NotBlank
    private String codigoPeca;

    @NotBlank
    private String fabricante;

    @NotNull
    private Integer quantidade;

    @NotNull
    private BigDecimal valorUnitario;

    @NotNull
    private Long manutencaoId;
}

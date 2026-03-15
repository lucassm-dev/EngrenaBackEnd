package com.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManutencaoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    private String descricaoServico;

    @NotNull
    private Integer tempoParadaMin;

    @NotNull
    private BigDecimal custoManutencao;

    @NotBlank
    private String observacoes;

    @NotNull
    private LocalDate dataManutencao;

    @NotNull
    private Long maquinaId;

    @NotNull
    private Long tecnicoId;

    @NotNull
    private Long ordemServicoId;
}

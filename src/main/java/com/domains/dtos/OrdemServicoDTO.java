package com.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdemServicoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    private String numeroOrdem;

    @NotBlank
    private String descricaoProblema;

    @NotNull
    private LocalDate dataAbertura;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataConclusao;
}

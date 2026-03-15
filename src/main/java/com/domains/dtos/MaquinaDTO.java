package com.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaquinaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String nome;

    @NotBlank
    private String modelo;

    @NotBlank
    private String fabricante;

    @NotBlank
    private String numeroSerie;

    @NotNull
    private LocalDate dataAquisicao;

    @NotNull
    private Integer anoFabricacao;

    @NotNull
    private Long setorId;
}

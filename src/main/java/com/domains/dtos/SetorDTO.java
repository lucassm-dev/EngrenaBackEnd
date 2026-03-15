package com.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetorDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = Update.class, message = "Id é obrigatório na atualização")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 70, message = "Nome deve ter no máximo 70 caracteres")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    private String descricao;

    @NotBlank(message = "Responsável é obrigatório")
    @Size(max = 100)
    private String responsavel;

    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 100)
    private String localizacao;
}

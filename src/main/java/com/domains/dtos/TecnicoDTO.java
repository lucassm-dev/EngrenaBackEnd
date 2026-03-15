package com.domains.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TecnicoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 70)
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 30)
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15)
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;
}

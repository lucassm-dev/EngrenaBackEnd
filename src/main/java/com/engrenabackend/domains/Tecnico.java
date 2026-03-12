package com.engrenabackend.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tecnico")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "tb_tecnico",
        sequenceName = "tb_tecnico",
        allocationSize = 1
)
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tecnico")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=70)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 30, unique = true)
    private String cpf;

    @NotBlank
    @Size(min = 10, max = 15)
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotBlank
    @Column(nullable = false, length = 30, unique = true)
    private String email;
}

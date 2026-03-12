package com.engrenabackend.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_setor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "tb_setor",
        sequenceName = "tb_setor",
        allocationSize = 1
)

public class Setor {

    @Id
    @GeneratedValue(strategy = GenerarionType.SEQUENCE, generator = "seq_setor")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=70)
    private String nome;

    @NotBlank
    @Column(nullable=false, length=100)
    private String descricao;

    @NotBlank
    @Column(nullable=false, length=100)
    private String responsavel;

    @NotBlank
    @Column(nullable=false, length=100)
    private String localizacao;

}

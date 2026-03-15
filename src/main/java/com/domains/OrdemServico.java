package com.engrenabackend.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_ordem_servico")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_ordem_servico",
        sequenceName = "seq_ordem_servico",
        allocationSize = 1
)
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem_servico")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=50, unique=true)
    private String numeroOrdem;

    @NotBlank
    @Column(nullable=false, length=200)
    private String descricaoProblema;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_conclusao", nullable = false)
    private LocalDate dataConclusao;

    @OneToOne(mappedBy = "ordemServico")
    private Manutencao manutencao;
}

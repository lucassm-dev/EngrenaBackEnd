package com.engrenabackend.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_manutencao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_manutencao",
        sequenceName = "seq_manutencao",
        allocationSize = 1
)

public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manutencao")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=100)
    private String descricaoServico;

    @NotNull
    @Column(name = "tempo_parada_min", nullable = false)
    private Integer tempoParadaMin;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    @Column(precision = 18, scale = 3, nullable = false)
    private BigDecimal custoManutencao;

    @NotBlank
    @Column(nullable=false, length=100)
    private String observacoes;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data", nullable = false)
    private LocalDate dataManutencao;
}

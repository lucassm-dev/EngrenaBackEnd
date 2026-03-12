package com.engrenabackend.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_historico_manutencao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_historico_manutencao",
        sequenceName = "seq_historico_manutencao",
        allocationSize = 1
)
public class HistoricoManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_manutencao")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=100)
    private String descricao;

    @NotNull
    @Column(name = "tempo_parada_min", nullable = false)
    private Integer tempoParadaMin;
}

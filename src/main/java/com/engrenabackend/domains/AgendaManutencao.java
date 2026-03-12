package com.engrenabackend.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_agenda_manutencao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_agenda_manutencao",
        sequenceName = "seq_agenda_manutencao",
        allocationSize = 1
)
public class AgendaManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agenda_manutencao")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=100)
    private String observacoes;
}

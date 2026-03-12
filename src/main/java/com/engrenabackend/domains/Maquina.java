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
@Table(name = "tb_maquina")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_maquina",
        sequenceName = "seq_maquina",
        allocationSize = 1
)
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_maquina")
    private Long id;

    @NotBlank
    @Column(nullable=false, length=70)
    private String nome;

    @NotBlank
    @Column(nullable=false, length=50)
    private String modelo;

    @NotBlank
    @Column(nullable=false, length=100)
    private String fabricante;

    @NotBlank
    @Column(nullable=false, length=30, unique = true)
    private String numeroSerie;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data", nullable = false)
    private LocalDate dataAquisicao;

    @NotNull
    @Column(name = "ano_fabricacao", nullable = false)
    private Integer anoFabricacao;
}

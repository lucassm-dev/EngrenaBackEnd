package com.engrenabackend.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_peca_utilizada")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_peca_utilizada",
        sequenceName = "seq_peca_utilizada",
        allocationSize = 1
)
public class PecaUtilizada {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_peca_utilizada")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nomePeca;

    @NotBlank
    @Column(nullable = false, length = 30, unique = true)
    private String codigoPeca;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String fabricante;

    @NotNull
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    @Column(precision = 18, scale = 3, nullable = false)
    private BigDecimal valorUnitario;
}

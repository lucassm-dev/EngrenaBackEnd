package com.services;

import com.domains.*;
import com.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private ManutencaoRepository manutencaoRepo;

    @Autowired
    private PecaUtilizadaRepository pecaRepo;

    @Autowired
    private TecnicoRepository tecnicoRepo;

    @Autowired
    private SetorRepository setorRepo;

    @Autowired
    private OrdemServicoRepository ordemServicoRepo;

    @Autowired
    private MaquinaRepository maquinaRepo;

    @Transactional
    public void initDB() {

        LocalDate hoje = LocalDate.now();

        Setor setor01 = new Setor(
                null,
                "Estamparia",
                "Setor responsável pela estampagem de peças metálicas",
                "Carlos Silva",
                "Galpão 1",
                null
        );

        setorRepo.save(setor01);


        Maquina maquina01 = new Maquina(
                null,
                "Prensa Hidráulica",
                "PHX-300",
                "Schuler",
                "SCH-987654",
                hoje.minusYears(2),
                2022,
                setor01,
                null
        );

        maquinaRepo.save(maquina01);


        Tecnico tecnico01 = new Tecnico(
                null,
                "João Mecânico",
                "12345678900",
                "17999998888",
                "joao@empresa.com",
                null
        );

        tecnicoRepo.save(tecnico01);


        OrdemServico ordem01 = new OrdemServico(
                null,
                "OS-2025-001",
                "Ruído anormal na prensa hidráulica",
                hoje.minusDays(1),
                hoje,
                hoje.plusDays(1),
                null
        );

        ordemServicoRepo.save(ordem01);


        Manutencao manutencao01 = new Manutencao(
                null,
                "Troca de rolamentos da prensa",
                120,
                new BigDecimal("850.000"),
                "Equipamento voltou a operar normalmente",
                hoje,
                maquina01,
                tecnico01,
                ordem01,
                null
        );

        manutencaoRepo.save(manutencao01);


        PecaUtilizada peca01 = new PecaUtilizada(
                null,
                "Rolamento Industrial",
                "ROL-5566",
                "SKF",
                2,
                new BigDecimal("120.000"),
                manutencao01
        );

        pecaRepo.save(peca01);

    }
}


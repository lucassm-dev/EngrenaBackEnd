package com.repositories;

import com.domains.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findByMaquina_Id(Long maquinaId);

    List<Manutencao> findByTecnico_Id(Long tecnicoId);

    List<Manutencao> findByDataManutencaoBetween(LocalDate inicio, LocalDate fim);

}

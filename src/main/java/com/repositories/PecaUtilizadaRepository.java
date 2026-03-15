package com.repositories;

import com.domains.PecaUtilizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaUtilizadaRepository extends JpaRepository<PecaUtilizada, Long> {

    List<PecaUtilizada> findByManutencao_Id(Long manutencaoId);

}

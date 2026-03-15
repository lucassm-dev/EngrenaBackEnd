package com.repositories;

import com.domains.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

    List<Maquina> findBySetor_Id(Long setorId);

    List<Maquina> findByFabricante(String fabricante);

}
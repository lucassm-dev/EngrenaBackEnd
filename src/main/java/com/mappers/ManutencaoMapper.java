package com.mappers;

import com.domains.Manutencao;
import com.domains.Maquina;
import com.domains.OrdemServico;
import com.domains.Tecnico;
import com.domains.dtos.ManutencaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.Collectors;

public class ManutencaoMapper {

    private ManutencaoMapper(){}

    public static ManutencaoDTO toDto(Manutencao e){
        if(e == null) return null;

        Long maquinaId = (e.getMaquina() == null) ? null : e.getMaquina().getId();
        Long tecnicoId = (e.getTecnico() == null) ? null : e.getTecnico().getId();
        Long ordemServicoId = (e.getOrdemServico() == null) ? null : e.getOrdemServico().getId();

        return new ManutencaoDTO(
                e.getId(),
                e.getDescricaoServico(),
                e.getTempoParadaMin(),
                e.getCustoManutencao(),
                e.getObservacoes(),
                e.getDataManutencao(),
                maquinaId,
                tecnicoId,
                ordemServicoId
        );
    }

    public static List<ManutencaoDTO> toDtoList(Collection<Manutencao> entities){
        if(entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(ManutencaoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<ManutencaoDTO> toDtoPage(Page<Manutencao> page){
        List<ManutencaoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }

    public static Manutencao toEntity(ManutencaoDTO dto, Maquina maquina, Tecnico tecnico, OrdemServico ordemServico){
        if(dto == null) return null;

        Manutencao e = new Manutencao();
        e.setId(dto.getId());
        e.setDescricaoServico(trim(dto.getDescricaoServico()));
        e.setTempoParadaMin(dto.getTempoParadaMin());
        e.setCustoManutencao(dto.getCustoManutencao());
        e.setObservacoes(trim(dto.getObservacoes()));
        e.setDataManutencao(dto.getDataManutencao());
        e.setMaquina(maquina);
        e.setTecnico(tecnico);
        e.setOrdemServico(ordemServico);

        return e;
    }

    public static void copyToEntity(
            ManutencaoDTO dto,
            Manutencao target,
            Maquina maquina,
            Tecnico tecnico,
            OrdemServico ordemServico){

        if(dto == null || target == null) return;

        target.setDescricaoServico(trim(dto.getDescricaoServico()));
        target.setTempoParadaMin(dto.getTempoParadaMin());
        target.setCustoManutencao(dto.getCustoManutencao());
        target.setObservacoes(trim(dto.getObservacoes()));
        target.setDataManutencao(dto.getDataManutencao());
        target.setMaquina(maquina);
        target.setTecnico(tecnico);
        target.setOrdemServico(ordemServico);
    }

    private static String trim(String s){
        return (s == null) ? null : s.trim();
    }
}

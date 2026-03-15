package com.mappers;

import com.domains.OrdemServico;
import com.domains.dtos.OrdemServicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.Collectors;

public class OrdemServicoMapper {

    private OrdemServicoMapper(){}

    public static OrdemServicoDTO toDto(OrdemServico e){
        if(e == null) return null;

        return new OrdemServicoDTO(
                e.getId(),
                e.getNumeroOrdem(),
                e.getDescricaoProblema(),
                e.getDataAbertura(),
                e.getDataInicio(),
                e.getDataConclusao()
        );
    }

    public static List<OrdemServicoDTO> toDtoList(Collection<OrdemServico> entities){
        if(entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(OrdemServicoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<OrdemServicoDTO> toDtoPage(Page<OrdemServico> page){
        List<OrdemServicoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }

    public static OrdemServico toEntity(OrdemServicoDTO dto){
        if(dto == null) return null;

        OrdemServico e = new OrdemServico();
        e.setId(dto.getId());
        e.setNumeroOrdem(trim(dto.getNumeroOrdem()));
        e.setDescricaoProblema(trim(dto.getDescricaoProblema()));
        e.setDataAbertura(dto.getDataAbertura());
        e.setDataInicio(dto.getDataInicio());
        e.setDataConclusao(dto.getDataConclusao());

        return e;
    }

    public static void copyToEntity(OrdemServicoDTO dto, OrdemServico target){
        if(dto == null || target == null) return;

        target.setNumeroOrdem(trim(dto.getNumeroOrdem()));
        target.setDescricaoProblema(trim(dto.getDescricaoProblema()));
        target.setDataAbertura(dto.getDataAbertura());
        target.setDataInicio(dto.getDataInicio());
        target.setDataConclusao(dto.getDataConclusao());
    }

    private static String trim(String s){
        return (s == null) ? null : s.trim();
    }
}

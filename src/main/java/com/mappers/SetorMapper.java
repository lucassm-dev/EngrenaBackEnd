package com.mappers;

import com.domains.Setor;
import com.domains.dtos.SetorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.Collectors;

public class SetorMapper {

    private SetorMapper(){}

    public static SetorDTO toDto(Setor e){
        if(e == null) return null;

        return new SetorDTO(
                e.getId(),
                e.getNome(),
                e.getDescricao(),
                e.getResponsavel(),
                e.getLocalizacao()
        );
    }

    public static List<SetorDTO> toDtoList(Collection<Setor> entities){
        if(entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(SetorMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<SetorDTO> toDtoPage(Page<Setor> page){
        List<SetorDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }

    public static Setor toEntity(SetorDTO dto){
        if(dto == null) return null;

        Setor e = new Setor();
        e.setId(dto.getId());
        e.setNome(trim(dto.getNome()));
        e.setDescricao(trim(dto.getDescricao()));
        e.setResponsavel(trim(dto.getResponsavel()));
        e.setLocalizacao(trim(dto.getLocalizacao()));

        return e;
    }

    public static void copyToEntity(SetorDTO dto, Setor target){
        if(dto == null || target == null) return;

        target.setNome(trim(dto.getNome()));
        target.setDescricao(trim(dto.getDescricao()));
        target.setResponsavel(trim(dto.getResponsavel()));
        target.setLocalizacao(trim(dto.getLocalizacao()));
    }

    private static String trim(String s){
        return (s == null) ? null : s.trim();
    }
}

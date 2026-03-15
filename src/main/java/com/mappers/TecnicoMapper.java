package com.mappers;

import com.domains.Tecnico;
import com.domains.dtos.TecnicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.Collectors;

public class TecnicoMapper {

    private TecnicoMapper(){}

    public static TecnicoDTO toDto(Tecnico e){
        if(e == null) return null;

        return new TecnicoDTO(
                e.getId(),
                e.getNome(),
                e.getCpf(),
                e.getTelefone(),
                e.getEmail()
        );
    }

    public static List<TecnicoDTO> toDtoList(Collection<Tecnico> entities){
        if(entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(TecnicoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<TecnicoDTO> toDtoPage(Page<Tecnico> page){
        List<TecnicoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }

    public static Tecnico toEntity(TecnicoDTO dto){
        if(dto == null) return null;

        Tecnico e = new Tecnico();
        e.setId(dto.getId());
        e.setNome(trim(dto.getNome()));
        e.setCpf(trim(dto.getCpf()));
        e.setTelefone(trim(dto.getTelefone()));
        e.setEmail(trim(dto.getEmail()));

        return e;
    }

    public static void copyToEntity(TecnicoDTO dto, Tecnico target){
        if(dto == null || target == null) return;

        target.setNome(trim(dto.getNome()));
        target.setCpf(trim(dto.getCpf()));
        target.setTelefone(trim(dto.getTelefone()));
        target.setEmail(trim(dto.getEmail()));
    }

    private static String trim(String s){
        return (s == null) ? null : s.trim();
    }
}

package com.mappers;

import com.domains.Maquina;
import com.domains.Setor;
import com.domains.dtos.MaquinaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaquinaMapper {

    private MaquinaMapper(){}

    public static MaquinaDTO toDto(Maquina e){
        if(e == null) return null;

        Long setorId = (e.getSetor() == null) ? null : e.getSetor().getId();

        return new MaquinaDTO(
                e.getId(),
                e.getNome(),
                e.getModelo(),
                e.getFabricante(),
                e.getNumeroSerie(),
                e.getDataAquisicao(),
                e.getAnoFabricacao(),
                setorId
        );
    }

    public static List<MaquinaDTO> toDtoList(Collection<Maquina> entities){
        if(entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(MaquinaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<MaquinaDTO> toDtoPage(Page<Maquina> page){
        List<MaquinaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }

    public static Maquina toEntity(MaquinaDTO dto, Setor setor){
        if(dto == null) return null;

        Maquina e = new Maquina();
        e.setId(dto.getId());
        e.setNome(trim(dto.getNome()));
        e.setModelo(trim(dto.getModelo()));
        e.setFabricante(trim(dto.getFabricante()));
        e.setNumeroSerie(trim(dto.getNumeroSerie()));
        e.setDataAquisicao(dto.getDataAquisicao());
        e.setAnoFabricacao(dto.getAnoFabricacao());
        e.setSetor(setor);

        return e;
    }

    public static Maquina toEntity(MaquinaDTO dto, Function<Long, Setor> setorResolver){
        if(dto == null) return null;
        Setor setor = (dto.getSetorId() == null) ? null : setorResolver.apply(dto.getSetorId());
        return toEntity(dto,setor);
    }

    public static void copyToEntity(MaquinaDTO dto, Maquina target, Setor setor){
        if(dto == null || target == null) return;

        target.setNome(trim(dto.getNome()));
        target.setModelo(trim(dto.getModelo()));
        target.setFabricante(trim(dto.getFabricante()));
        target.setNumeroSerie(trim(dto.getNumeroSerie()));
        target.setDataAquisicao(dto.getDataAquisicao());
        target.setAnoFabricacao(dto.getAnoFabricacao());
        target.setSetor(setor);
    }

    private static String trim(String s){
        return (s == null) ? null : s.trim();
    }
}

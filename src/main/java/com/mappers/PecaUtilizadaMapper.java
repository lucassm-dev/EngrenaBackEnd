package com.mappers;

import com.domains.Manutencao;
import com.domains.PecaUtilizada;
import com.domains.dtos.PecaUtilizadaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PecaUtilizadaMapper {

    private PecaUtilizadaMapper(){}

    public static PecaUtilizadaDTO toDto(PecaUtilizada e){
        if(e == null) return null;

        Long manutencaoId = (e.getManutencao() == null) ? null : e.getManutencao().getId();

        return new PecaUtilizadaDTO(
                e.getId(),
                e.getNomePeca(),
                e.getCodigoPeca(),
                e.getFabricante(),
                e.getQuantidade(),
                e.getValorUnitario(),
                manutencaoId
        );
    }

    public static List<PecaUtilizadaDTO> toDtoList(Collection<PecaUtilizada> entities){
        if(entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(PecaUtilizadaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<PecaUtilizadaDTO> toDtoPage(Page<PecaUtilizada> page){
        List<PecaUtilizadaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }

    public static PecaUtilizada toEntity(PecaUtilizadaDTO dto, Manutencao manutencao){
        if(dto == null) return null;

        PecaUtilizada e = new PecaUtilizada();
        e.setId(dto.getId());
        e.setNomePeca(trim(dto.getNomePeca()));
        e.setCodigoPeca(trim(dto.getCodigoPeca()));
        e.setFabricante(trim(dto.getFabricante()));
        e.setQuantidade(dto.getQuantidade());
        e.setValorUnitario(dto.getValorUnitario());
        e.setManutencao(manutencao);

        return e;
    }

    public static PecaUtilizada toEntity(PecaUtilizadaDTO dto, Function<Long, Manutencao> manutencaoResolver){
        if(dto == null) return null;
        Manutencao manutencao = (dto.getManutencaoId() == null) ? null : manutencaoResolver.apply(dto.getManutencaoId());
        return toEntity(dto,manutencao);
    }

    public static void copyToEntity(PecaUtilizadaDTO dto, PecaUtilizada target, Manutencao manutencao){
        if(dto == null || target == null) return;

        target.setNomePeca(trim(dto.getNomePeca()));
        target.setCodigoPeca(trim(dto.getCodigoPeca()));
        target.setFabricante(trim(dto.getFabricante()));
        target.setQuantidade(dto.getQuantidade());
        target.setValorUnitario(dto.getValorUnitario());
        target.setManutencao(manutencao);
    }

    private static String trim(String s){
        return (s == null) ? null : s.trim();
    }
}

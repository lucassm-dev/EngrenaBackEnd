package com.services;

import com.domains.Tecnico;
import com.domains.dtos.TecnicoDTO;
import com.mappers.TecnicoMapper;
import com.repositories.TecnicoRepository;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepo;

    public TecnicoService(TecnicoRepository tecnicoRepo){
        this.tecnicoRepo = tecnicoRepo;
    }

    @Transactional(readOnly = true)
    public List<TecnicoDTO> findAll(){
        return TecnicoMapper.toDtoList(tecnicoRepo.findAll());
    }

    @Transactional(readOnly = true)
    public TecnicoDTO findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id do técnico é obrigatório");
        }

        return tecnicoRepo.findById(id)
                .map(TecnicoMapper::toDto)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Técnico não encontrado: id=" + id));
    }

    @Transactional
    public TecnicoDTO create(TecnicoDTO dto){

        dto.setId(null);

        Tecnico entity = TecnicoMapper.toEntity(dto);

        return TecnicoMapper.toDto(tecnicoRepo.save(entity));
    }

    @Transactional
    public TecnicoDTO update(Long id, TecnicoDTO dto){

        Tecnico entity = tecnicoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Técnico não encontrado: id=" + id));

        TecnicoMapper.copyToEntity(dto,entity);

        return TecnicoMapper.toDto(tecnicoRepo.save(entity));
    }

    @Transactional
    public void delete(Long id){

        Tecnico entity = tecnicoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Técnico não encontrado: id=" + id));

        tecnicoRepo.delete(entity);
    }
}

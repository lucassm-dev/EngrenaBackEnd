package com.services;

import com.domains.Setor;
import com.domains.dtos.SetorDTO;
import com.mappers.SetorMapper;
import com.repositories.SetorRepository;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SetorService {

    private final SetorRepository setorRepo;

    public SetorService(SetorRepository setorRepo) {
        this.setorRepo = setorRepo;
    }

    @Transactional(readOnly = true)
    public List<SetorDTO> findAll(){
        return SetorMapper.toDtoList(setorRepo.findAll());
    }

    @Transactional(readOnly = true)
    public SetorDTO findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id do setor é obrigatório");
        }

        return setorRepo.findById(id)
                .map(SetorMapper::toDto)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Setor não encontrado: id=" + id));
    }

    @Transactional
    public SetorDTO create(SetorDTO dto){
        if(dto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Dados do setor são obrigatórios");
        }

        dto.setId(null);

        Setor entity = SetorMapper.toEntity(dto);

        Setor saved = setorRepo.save(entity);

        return SetorMapper.toDto(saved);
    }

    @Transactional
    public SetorDTO update(Long id, SetorDTO dto){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id do setor é obrigatório");
        }

        Setor entity = setorRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Setor não encontrado: id=" + id));

        SetorMapper.copyToEntity(dto,entity);

        return SetorMapper.toDto(setorRepo.save(entity));
    }

    @Transactional
    public void delete(Long id){

        Setor entity = setorRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Setor não encontrado: id=" + id));

        try{
            setorRepo.delete(entity);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Setor possui máquinas vinculadas");
        }
    }
}

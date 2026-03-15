package com.services;

import com.domains.Maquina;
import com.domains.Setor;
import com.domains.dtos.MaquinaDTO;
import com.mappers.MaquinaMapper;
import com.repositories.MaquinaRepository;
import com.repositories.SetorRepository;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class MaquinaService {

    private final MaquinaRepository maquinaRepo;
    private final SetorRepository setorRepo;

    public MaquinaService(MaquinaRepository maquinaRepo, SetorRepository setorRepo){
        this.maquinaRepo = maquinaRepo;
        this.setorRepo = setorRepo;
    }

    private Setor resolveSetor(Long id){
        return setorRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Setor não encontrado: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<MaquinaDTO> findAll(){
        return MaquinaMapper.toDtoList(maquinaRepo.findAll());
    }

    @Transactional(readOnly = true)
    public MaquinaDTO findById(Long id) {

        Maquina entity = maquinaRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Máquina não encontrada: id=" + id));

        return MaquinaMapper.toDto(entity);
    }

    @Transactional
    public MaquinaDTO create(MaquinaDTO dto){

        dto.setId(null);

        Maquina entity = MaquinaMapper.toEntity(dto,this::resolveSetor);

        return MaquinaMapper.toDto(maquinaRepo.save(entity));
    }

    @Transactional
    public MaquinaDTO update(Long id, MaquinaDTO dto){

        Maquina entity = maquinaRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Máquina não encontrada"));

        MaquinaMapper.copyToEntity(dto,entity,resolveSetor(dto.getSetorId()));

        return MaquinaMapper.toDto(maquinaRepo.save(entity));
    }

    @Transactional
    public void delete(Long id){

        if(id == null){
            throw new IllegalArgumentException("Id da máquina é obrigatório");
        }

        Maquina entity = maquinaRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Máquina não encontrada: id=" + id));

        try{
            maquinaRepo.delete(entity);
        }
        catch(org.springframework.dao.DataIntegrityViolationException ex){
            throw new org.springframework.dao.DataIntegrityViolationException(
                    "Máquina possui vínculos (manutenções) e não pode ser removida: id=" + id,
                    ex
            );
        }
    }
}

package com.services;

import com.domains.OrdemServico;
import com.domains.dtos.OrdemServicoDTO;
import com.mappers.OrdemServicoMapper;
import com.repositories.OrdemServicoRepository;
import com.services.exceptions.DataIntegrityViolationException;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemRepo;

    public OrdemServicoService(OrdemServicoRepository ordemRepo){
        this.ordemRepo = ordemRepo;
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> findAll(){
        return OrdemServicoMapper.toDtoList(ordemRepo.findAll());
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO findById(Long id){

        OrdemServico entity = ordemRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Ordem de serviço não encontrada: id=" + id));

        return OrdemServicoMapper.toDto(entity);
    }

    @Transactional
    public OrdemServicoDTO create(OrdemServicoDTO dto){

        dto.setId(null);

        OrdemServico entity = OrdemServicoMapper.toEntity(dto);

        return OrdemServicoMapper.toDto(ordemRepo.save(entity));
    }

    @Transactional
    public OrdemServicoDTO update(Long id, OrdemServicoDTO dto){

        OrdemServico entity = ordemRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Ordem de serviço não encontrada: id=" + id));

        OrdemServicoMapper.copyToEntity(dto, entity);

        return OrdemServicoMapper.toDto(ordemRepo.save(entity));
    }

    @Transactional
    public void delete(Long id){

        OrdemServico entity = ordemRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Ordem de serviço não encontrada: id=" + id));

        try{
            ordemRepo.delete(entity);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(
                    "Ordem de serviço possui vínculos (manutenção) e não pode ser removida: id=" + id,
                    ex
            );
        }
    }
}

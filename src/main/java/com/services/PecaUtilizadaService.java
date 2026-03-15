package com.services;

import com.domains.Manutencao;
import com.domains.PecaUtilizada;
import com.domains.dtos.PecaUtilizadaDTO;
import com.mappers.PecaUtilizadaMapper;
import com.repositories.ManutencaoRepository;
import com.repositories.PecaUtilizadaRepository;
import com.services.exceptions.DataIntegrityViolationException;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PecaUtilizadaService {

    private final PecaUtilizadaRepository pecaRepo;
    private final ManutencaoRepository manutencaoRepo;

    public PecaUtilizadaService(
            PecaUtilizadaRepository pecaRepo,
            ManutencaoRepository manutencaoRepo){

        this.pecaRepo = pecaRepo;
        this.manutencaoRepo = manutencaoRepo;
    }

    private Manutencao resolveManutencao(Long id){
        return manutencaoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Manutenção não encontrada: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<PecaUtilizadaDTO> findAll(){
        return PecaUtilizadaMapper.toDtoList(pecaRepo.findAll());
    }

    @Transactional(readOnly = true)
    public PecaUtilizadaDTO findById(Long id){

        PecaUtilizada entity = pecaRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Peça utilizada não encontrada: id=" + id));

        return PecaUtilizadaMapper.toDto(entity);
    }

    @Transactional
    public PecaUtilizadaDTO create(PecaUtilizadaDTO dto){

        dto.setId(null);

        PecaUtilizada entity = PecaUtilizadaMapper.toEntity(dto,this::resolveManutencao);

        return PecaUtilizadaMapper.toDto(pecaRepo.save(entity));
    }

    @Transactional
    public PecaUtilizadaDTO update(Long id, PecaUtilizadaDTO dto){

        PecaUtilizada entity = pecaRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Peça utilizada não encontrada: id=" + id));

        Manutencao manutencao = resolveManutencao(dto.getManutencaoId());

        PecaUtilizadaMapper.copyToEntity(dto, entity, manutencao);

        return PecaUtilizadaMapper.toDto(pecaRepo.save(entity));
    }

    @Transactional
    public void delete(Long id){

        PecaUtilizada entity = pecaRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Peça utilizada não encontrada: id=" + id));

        try{
            pecaRepo.delete(entity);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(
                    "Peça utilizada possui vínculos e não pode ser removida: id=" + id,
                    ex
            );
        }
    }
}

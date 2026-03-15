package com.services;

import com.domains.Manutencao;
import com.domains.Maquina;
import com.domains.OrdemServico;
import com.domains.Tecnico;
import com.domains.dtos.ManutencaoDTO;
import com.mappers.ManutencaoMapper;
import com.repositories.ManutencaoRepository;
import com.repositories.MaquinaRepository;
import com.repositories.OrdemServicoRepository;
import com.repositories.TecnicoRepository;
import com.services.exceptions.DataIntegrityViolationException;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepo;
    private final MaquinaRepository maquinaRepo;
    private final TecnicoRepository tecnicoRepo;
    private final OrdemServicoRepository ordemRepo;

    public ManutencaoService(
            ManutencaoRepository manutencaoRepo,
            MaquinaRepository maquinaRepo,
            TecnicoRepository tecnicoRepo,
            OrdemServicoRepository ordemRepo) {

        this.manutencaoRepo = manutencaoRepo;
        this.maquinaRepo = maquinaRepo;
        this.tecnicoRepo = tecnicoRepo;
        this.ordemRepo = ordemRepo;
    }

    private Maquina resolveMaquina(Long id){
        return maquinaRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Máquina não encontrada: id=" + id));
    }

    private Tecnico resolveTecnico(Long id){
        return tecnicoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado: id=" + id));
    }

    private OrdemServico resolveOS(Long id){
        return ordemRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Ordem de serviço não encontrada: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<ManutencaoDTO> findAll(){
        return ManutencaoMapper.toDtoList(manutencaoRepo.findAll());
    }

    @Transactional(readOnly = true)
    public ManutencaoDTO findById(Long id){

        Manutencao entity = manutencaoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Manutenção não encontrada: id=" + id));

        return ManutencaoMapper.toDto(entity);
    }

    @Transactional
    public ManutencaoDTO create(ManutencaoDTO dto){

        dto.setId(null);

        Manutencao entity = ManutencaoMapper.toEntity(
                dto,
                resolveMaquina(dto.getMaquinaId()),
                resolveTecnico(dto.getTecnicoId()),
                resolveOS(dto.getOrdemServicoId())
        );

        return ManutencaoMapper.toDto(manutencaoRepo.save(entity));
    }

    @Transactional
    public ManutencaoDTO update(Long id, ManutencaoDTO dto){

        Manutencao entity = manutencaoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Manutenção não encontrada: id=" + id));

        ManutencaoMapper.copyToEntity(
                dto,
                entity,
                resolveMaquina(dto.getMaquinaId()),
                resolveTecnico(dto.getTecnicoId()),
                resolveOS(dto.getOrdemServicoId())
        );

        return ManutencaoMapper.toDto(manutencaoRepo.save(entity));
    }

    @Transactional
    public void delete(Long id){

        Manutencao entity = manutencaoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Manutenção não encontrada: id=" + id));

        try{
            manutencaoRepo.delete(entity);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(
                    "Manutenção possui vínculos (peças utilizadas) e não pode ser removida: id=" + id,
                    ex
            );
        }
    }
}

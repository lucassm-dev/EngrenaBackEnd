package com.resources;

import com.domains.dtos.OrdemServicoDTO;
import com.services.OrdemServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/ordens-servico")
public class OrdemServicoResource {

    private final OrdemServicoService service;

    public OrdemServicoResource(OrdemServicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> create(
            @RequestBody @Validated(OrdemServicoDTO.Create.class) OrdemServicoDTO dto) {

        OrdemServicoDTO created = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(OrdemServicoDTO.Update.class) OrdemServicoDTO dto) {

        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

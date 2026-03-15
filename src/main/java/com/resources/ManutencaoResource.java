package com.resources;

import com.domains.dtos.ManutencaoDTO;
import com.services.ManutencaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/manutencoes")
public class ManutencaoResource {

    private final ManutencaoService service;

    public ManutencaoResource(ManutencaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ManutencaoDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManutencaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ManutencaoDTO> create(
            @RequestBody @Validated(ManutencaoDTO.Create.class) ManutencaoDTO dto) {

        ManutencaoDTO created = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManutencaoDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(ManutencaoDTO.Update.class) ManutencaoDTO dto) {

        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

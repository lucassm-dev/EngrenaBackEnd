package com.resources;

import com.domains.dtos.PecaUtilizadaDTO;
import com.services.PecaUtilizadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/pecas-utilizadas")
public class PecaUtilizadaResource {

    private final PecaUtilizadaService service;

    public PecaUtilizadaResource(PecaUtilizadaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PecaUtilizadaDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaUtilizadaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PecaUtilizadaDTO> create(
            @RequestBody @Validated(PecaUtilizadaDTO.Create.class) PecaUtilizadaDTO dto) {

        PecaUtilizadaDTO created = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PecaUtilizadaDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(PecaUtilizadaDTO.Update.class) PecaUtilizadaDTO dto) {

        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

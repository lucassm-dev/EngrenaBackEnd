package com.resources;

import com.domains.dtos.TecnicoDTO;
import com.services.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/tecnicos")
public class TecnicoResource {

    private final TecnicoService service;

    public TecnicoResource(TecnicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(
            @RequestBody @Validated(TecnicoDTO.Create.class) TecnicoDTO dto) {

        TecnicoDTO created = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(TecnicoDTO.Update.class) TecnicoDTO dto) {

        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

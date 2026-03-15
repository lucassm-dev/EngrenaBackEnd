package com.resources;

import com.domains.dtos.SetorDTO;
import com.services.SetorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/setores")
public class SetorResource {

    private final SetorService service;

    public SetorResource(SetorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SetorDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SetorDTO> create(
            @RequestBody @Validated(SetorDTO.Create.class) SetorDTO dto) {

        SetorDTO created = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(SetorDTO.Update.class) SetorDTO dto) {

        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
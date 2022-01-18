package io.codero.parcelservice.controller;


import io.codero.parcelservice.dto.ParcelDto;
import io.codero.parcelservice.facade.ParcelFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/parcel")
public class ParcelController {
    private final ParcelFacade facade;

    @PostMapping
    public UUID save(@RequestBody ParcelDto dto) {
        return facade.save(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelDto> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(facade.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParcelDto>> getAll() {
        return ResponseEntity.ok().body(facade.getAll());
    }

    @PutMapping
    public void update(@RequestBody final ParcelDto dto) {
        facade.update(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        facade.delete(id);
    }

    @DeleteMapping
    public void deleteAll() {
        facade.deleteAll();
    }
}

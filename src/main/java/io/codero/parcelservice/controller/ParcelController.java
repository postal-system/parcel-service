package io.codero.parcelservice.controller;

import io.codero.parcelservice.dto.ParcelDto;
import io.codero.parcelservice.facade.ParcelFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/parcels")
public class ParcelController {
    private final ParcelFacade facade;

    @PostMapping
    public UUID save(@RequestBody ParcelDto dto) {
        return facade.save(dto);
    }

    @GetMapping("/{id}")
    public ParcelDto getById(@PathVariable("id") UUID id) {
        return facade.getById(id);
    }

    @GetMapping
    public List<ParcelDto> getAll() {
        return facade.getAll();
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

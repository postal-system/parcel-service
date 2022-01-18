package io.codero.parcelservice.facade;

import io.codero.parcelservice.dto.ParcelDto;
import io.codero.parcelservice.entity.Parcel;
import io.codero.parcelservice.mapper.ParcelMapper;
import io.codero.parcelservice.service.ParcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParcelFacade {
    private final ParcelService parcelService;
    private final ParcelMapper parcelMapper;

    public UUID save(ParcelDto dto) {
        Parcel parcel = parcelMapper.toEntity(dto);
        return parcelService.save(parcel);
    }

    public ParcelDto getById(UUID id) {
        Parcel parcel = parcelService.getById(id);
        return parcelMapper.toDto(parcel);
    }

    public List<ParcelDto> getAll() {
        return parcelService.getAll().stream().map(parcelMapper::toDto).collect(Collectors.toList());
    }

    public void update(ParcelDto dto) {
        Parcel parcel = parcelMapper.toEntity(dto);
        parcelService.update(parcel);
    }

    public void delete(UUID id) {
        parcelService.delete(id);
    }

    public void deleteAll() {
        parcelService.deleteAll();
    }
}

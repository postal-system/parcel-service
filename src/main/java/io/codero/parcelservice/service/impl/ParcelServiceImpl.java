package io.codero.parcelservice.service.impl;

import io.codero.parcelservice.entity.Parcel;
import io.codero.parcelservice.exception.CastIdAlreadyExistException;
import io.codero.parcelservice.exception.CastNotFoundException;
import io.codero.parcelservice.repository.ParcelRepository;
import io.codero.parcelservice.service.ParcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final ParcelRepository repository;

    @Override
    public UUID save(Parcel parcel) {
        if (repository.existsById(parcel.getId())) {
            throw new CastIdAlreadyExistException(String.format("Object with ID: %s already exist, ", parcel.getId()));
        }
        log.info("save {} to DB", parcel);
        return repository.save(parcel).getId();
    }

    @Override
    public Parcel getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CastNotFoundException("Not found by ID: " + id));
    }

    @Override
    public List<Parcel> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Parcel parcel) {
        if (!repository.existsById(parcel.getId())) {
            throw new CastNotFoundException(String.format("Object with ID: %s cannot by update", parcel.getId()));
        }
        repository.save(parcel);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}

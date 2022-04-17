package io.codero.parcelservice.service.impl;

import io.codero.parcelservice.entity.Parcel;
import io.codero.parcelservice.exception.IdAlreadyExistException;
import io.codero.parcelservice.exception.NotFoundException;
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
    public synchronized UUID save(Parcel parcel) {
        if (repository.existsById(parcel.getId())) {
            throw new IdAlreadyExistException(String.format("Parcel with ID: %s already exist, ", parcel.getId()));
        }
        log.info("save {} to DB", parcel);
        return repository.save(parcel).getId();
    }

    @Override
    public Parcel getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found be ID: " + id));
    }

    @Override
    public List<Parcel> getAll() {
        return repository.findAll();
    }

    @Override
    public synchronized void update(Parcel parcel) {
        if (!repository.existsById(parcel.getId())) {
            throw new NotFoundException(String.format("Parcel with ID: %s cannot be update", parcel.getId()));
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

package io.codero.parcelservice.service;

import io.codero.parcelservice.entity.Parcel;

import java.util.List;
import java.util.UUID;

public interface ParcelService {
    UUID save(Parcel parcel);

    Parcel getById(UUID id);

    void update(Parcel parcel);

    void delete(UUID id);

    void deleteAll();

    List<Parcel> getAll();
}

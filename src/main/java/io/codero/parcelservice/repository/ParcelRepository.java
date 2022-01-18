package io.codero.parcelservice.repository;

import io.codero.parcelservice.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParcelRepository extends JpaRepository<Parcel, UUID> {
}

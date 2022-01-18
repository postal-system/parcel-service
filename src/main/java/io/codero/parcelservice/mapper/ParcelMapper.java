package io.codero.parcelservice.mapper;

import io.codero.parcelservice.dto.ParcelDto;
import io.codero.parcelservice.entity.Parcel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ParcelMapper {
    ParcelDto toDto(Parcel parcel);

    Parcel toEntity(ParcelDto dto);
}

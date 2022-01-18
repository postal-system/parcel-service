package io.codero.parcelservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ParcelDto {
    private UUID id;
    private String sender;
    private String receiver;
    private Integer postOfficeId;
    private Instant timestamp;
    private RawParcelDto rawParcelDto;
}

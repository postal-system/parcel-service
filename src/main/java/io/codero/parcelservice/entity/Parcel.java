package io.codero.parcelservice.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.codero.parcelservice.dto.RawParcelDto;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Parcel {
    @Id
    @Column(name = "id", columnDefinition = "pg-uuid")
    private UUID id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(name = "post_office_id", nullable = false)
    private Integer postOfficeId;

    @Column(name = "time_stamp")
    private Instant timestamp;

    @Type(type = "jsonb")
    @Column(name = "raw_parcel", nullable = false, columnDefinition = "jsonb")
    private RawParcelDto rawParcelDto;
}

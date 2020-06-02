package com.crewmaker.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="EventPlaceImage")
public class EventPlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EventPlaceImageID")
    private Long eventPlaceImageID;

    @Column(name="Name")
    private String name;

    @Column(name="Type")
    private String type;

    @Lob
    @Column(name="BinaryData")
    private byte[] binaryData;

    public EventPlaceImage(String originalFilename, String contentType, byte[] compressBytes) {
        this.name = originalFilename;
        this.type = contentType;
        this.binaryData = compressBytes;
    }
}

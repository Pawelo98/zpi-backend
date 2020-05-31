package com.crewmaker.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="UserProfileImage")
public class UserProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ImageID")
    private Long imageId;

    @Column(name="Name")
    private String name;

    @Column(name="Type")
    private String type;

    @Lob
    @Column(name="BinaryData")
    private byte[] binaryData;

    public UserProfileImage(String originalFilename, String contentType, byte[] compressBytes) {
        this.name = originalFilename;
        this.type = contentType;
        this.binaryData = compressBytes;
    }
}

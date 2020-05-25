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
@Table(name="UserProfileImage")
public class UserProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ImageID")
    private Long imageId;

    @Column(name="Name")
    private String name;

    @Lob
    @Column(name="BinaryData")
    private byte[] binaryData;

}

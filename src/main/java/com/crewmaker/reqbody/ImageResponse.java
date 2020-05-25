package com.crewmaker.reqbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageResponse {
    private byte[] binaryData;
    private String name;
}
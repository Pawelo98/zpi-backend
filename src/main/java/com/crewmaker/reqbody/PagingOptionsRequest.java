package com.crewmaker.reqbody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingOptionsRequest {
    private int activePage;
    private int size;
}

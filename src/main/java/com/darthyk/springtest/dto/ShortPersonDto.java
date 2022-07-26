package com.darthyk.springtest.dto;

import lombok.Data;

@Data
public class ShortPersonDto {
    private String name;
    private String lastname;
    private Long parentId;
}

package com.darthyk.springtest.dto;

import java.util.List;
import lombok.Data;

@Data
public class CarDto {
    private String name;
    private DocumentDto document;
    private List<BoxDto> boxes;
}

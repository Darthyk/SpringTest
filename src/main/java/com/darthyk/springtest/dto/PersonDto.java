package com.darthyk.springtest.dto;

import java.util.List;
import lombok.Data;

@Data
public class PersonDto {
    private String name;
    private String lastname;
    private List<String> parentNames;
}

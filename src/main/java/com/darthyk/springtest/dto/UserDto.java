package com.darthyk.springtest.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private List<CarDto> cars;
    private DocumentDto document;

    @Override
    public String toString() {
        return  "User {name: " + username + "}";
    }
}

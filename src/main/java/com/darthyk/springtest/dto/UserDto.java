package com.darthyk.springtest.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String lastname;

    @Override
    public String toString() {
        return  "User {name:" + name + ", lastname:" + lastname + "}";
    }
}

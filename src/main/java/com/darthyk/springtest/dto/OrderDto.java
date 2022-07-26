package com.darthyk.springtest.dto;

import com.darthyk.springtest.model.Status;
import lombok.Data;

@Data
public class OrderDto {
    private Status status;
    private UserDto user;
    private CarDto car;
}

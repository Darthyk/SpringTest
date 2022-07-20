package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.CarDto;

public interface CarService {
    CarDto createCar(CarDto carDto, Long id);
}

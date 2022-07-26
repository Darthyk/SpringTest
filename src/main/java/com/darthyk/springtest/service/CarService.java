package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.CarDto;
import com.darthyk.springtest.model.Car;

public interface CarService {
    CarDto createCar(CarDto carDto, Long id);
    Car getCarById(Long id);
}

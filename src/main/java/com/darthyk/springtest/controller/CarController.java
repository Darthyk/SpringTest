package com.darthyk.springtest.controller;

import com.darthyk.springtest.dto.CarDto;
import com.darthyk.springtest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping("/v1/cars")
    public String createCar(@RequestBody CarDto carDto, @RequestParam("userId") Long userId) {
        carService.createCar(carDto, userId);
        return "OK";
    }
}

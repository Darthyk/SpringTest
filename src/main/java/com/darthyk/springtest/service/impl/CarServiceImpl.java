package com.darthyk.springtest.service.impl;

import com.darthyk.springtest.dto.CarDto;
import com.darthyk.springtest.model.Car;
import com.darthyk.springtest.model.User;
import com.darthyk.springtest.repository.CarRepository;
import com.darthyk.springtest.service.CarService;
import com.darthyk.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CarServiceImpl implements CarService {

    public CarServiceImpl(CarRepository carRepository, UserService userService) {
        this.repository = carRepository;
        this.userService = userService;
    }

    @Autowired
    private CarRepository repository;
    @Autowired
    private UserService userService;

    public CarDto createCar(CarDto carDto, Long userId) {
        Car car = new Car();
        car.setName(carDto.getName());
        User user = userService.getUserById(userId);
        car.setUser(user);
        repository.save(car);
        return carDto;
    }

    @Override public Car getCarById(Long id) {
        return repository.getCarById(id);
    }
}

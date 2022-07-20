package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.CarDto;
import com.darthyk.springtest.model.Car;
import com.darthyk.springtest.model.User;
import com.darthyk.springtest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
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
}

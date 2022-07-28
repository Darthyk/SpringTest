package com.darthyk.springtest.service.impl;

import com.darthyk.springtest.dto.CarDto;
import com.darthyk.springtest.dto.DocumentDto;
import com.darthyk.springtest.dto.UserDto;
import com.darthyk.springtest.model.Box;
import com.darthyk.springtest.model.Car;
import com.darthyk.springtest.model.Document;
import com.darthyk.springtest.model.User;
import com.darthyk.springtest.repository.UserRepository;
import com.darthyk.springtest.service.UserService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override public void createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setCars(userDto.getCars().stream()
                .map(carDto -> {
                    Car car = new Car();
                    car.setName(carDto.getName());
                    car.setUser(user);
                    Document document = new Document();
                    document.setSerialNumber(carDto.getDocument().getSerialNUmber());
                    document.setCar(car);
                    car.setDocument(document);
                    List<Box> boxes = carDto.getBoxes().stream().map(boxDto -> {
                        Box box = new Box();
                        box.setNumber(boxDto.getNumber());
                        box.setCars(List.of(car));
                        return box;
                    }).collect(Collectors.toList());
                    car.setBoxes(boxes);
                    return car;
                }).collect(Collectors.toList()));
        userRepository.save(user);
    }

    @Override
    public void createSimpleUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getUserByUsername(String username) {
        List<User> users = userRepository.findUsersByUsername(username);
        if (!users.isEmpty()) {
            return users.stream()
                    .map(user -> {
                        UserDto userDto = new UserDto();
                        userDto.setUsername(user.getUsername());
                        userDto.setCars(user.getCars().stream()
                                .map(car -> {
                                    CarDto carDto = new CarDto();
                                    carDto.setName(car.getName());
                                    DocumentDto documentDto = new DocumentDto();
                                    documentDto.setSerialNUmber(car.getDocument().getSerialNumber());
                                    carDto.setDocument(documentDto);
                                    return carDto;
                                }).collect(Collectors.toList()));
                        return userDto;
                    }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }
}

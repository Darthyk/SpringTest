package com.darthyk.springtest.configuration;

import com.darthyk.springtest.repository.CarRepository;
import com.darthyk.springtest.repository.OrderRepository;
import com.darthyk.springtest.repository.PersonRepository;
import com.darthyk.springtest.repository.UserRepository;
import com.darthyk.springtest.service.CarService;
import com.darthyk.springtest.service.CarServiceImpl;
import com.darthyk.springtest.service.OrderService;
import com.darthyk.springtest.service.OrderServiceImpl;
import com.darthyk.springtest.service.PersonService;
import com.darthyk.springtest.service.PersonServiceImpl;
import com.darthyk.springtest.service.UserService;
import com.darthyk.springtest.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public CarService carService(CarRepository carRepository, UserService userService) {
        return new CarServiceImpl(carRepository, userService);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository, UserService userService, CarService carService) {
        return new OrderServiceImpl(orderRepository, userService, carService);
    }

    @Bean
    public PersonService personService(PersonRepository personRepository) {
        return new PersonServiceImpl(personRepository);
    }
}

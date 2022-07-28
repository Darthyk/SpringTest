package com.darthyk.springtest.configuration;

import com.darthyk.springtest.repository.CarRepository;
import com.darthyk.springtest.repository.ItemRepository;
import com.darthyk.springtest.repository.OrderRepository;
import com.darthyk.springtest.repository.PersonRepository;
import com.darthyk.springtest.repository.UserRepository;
import com.darthyk.springtest.service.CarService;
import com.darthyk.springtest.service.impl.CarServiceImpl;
import com.darthyk.springtest.service.ItemService;
import com.darthyk.springtest.service.impl.ItemServiceImpl;
import com.darthyk.springtest.service.OrderService;
import com.darthyk.springtest.service.impl.OrderServiceImpl;
import com.darthyk.springtest.service.PersonService;
import com.darthyk.springtest.service.impl.PersonServiceImpl;
import com.darthyk.springtest.service.UserService;
import com.darthyk.springtest.service.impl.UserServiceImpl;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
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

    @Bean
    public ItemService itemService(EntityManager entityManager, ItemRepository itemRepository) {
        return new ItemServiceImpl(entityManager, itemRepository);
    }
}

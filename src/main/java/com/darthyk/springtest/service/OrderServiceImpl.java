package com.darthyk.springtest.service;

import com.darthyk.springtest.model.Car;
import com.darthyk.springtest.model.Order;
import com.darthyk.springtest.model.Status;
import com.darthyk.springtest.model.User;
import com.darthyk.springtest.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, CarService carService) {
        this.repository = orderRepository;
        this.userService = userService;
        this.carService = carService;
    }

    private OrderRepository repository;
    private UserService userService;
    private CarService carService;

    @Override public Order createOrder(Long userId) {
        Order order = new Order();

        User user = userService.getUserById(userId);
        order.setUser(user);
        order.setStatus(Status.START);

        return repository.save(order);
    }

    @Override public Order handleCarInProd(Long orderId, Long carId) {
        Order order = repository.getOrderById(orderId);
        Car carById = carService.getCarById(carId);
        order.setCar(carById);
        order.setStatus(Status.CAR_IN_PROD);
        return repository.save(order);
    }

    @Override public Order handleCarProdCompleted(Long carId) {
        Car car = carService.getCarById(carId);
        Order order = repository.findOrderByCar(car);
        order.setStatus(Status.CAR_PROD_COMPLETED);
        return repository.save(order);
    }

    @Override public Order handleCarDelivered(Long carId) {
        Car car = carService.getCarById(carId);
        Order order = repository.findOrderByCar(car);
        order.setStatus(Status.CAR_DELIVERED);
        return repository.save(order);
    }

    @Override public Order handleCarFinished(Long carId) {
        Car car = carService.getCarById(carId);
        Order order = repository.findOrderByCar(car);
        order.setStatus(Status.FINISH);
        return repository.save(order);
    }
}

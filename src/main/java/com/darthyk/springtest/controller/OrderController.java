package com.darthyk.springtest.controller;

import com.darthyk.springtest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public String createOrder(@RequestParam("userId") Long userId) {
        orderService.createOrder(userId);
        return "OK";
    }

    @PutMapping("/{orderId}/status/car_in_prod")
    public String handleCarInProd(@PathVariable("orderId") Long orderId, @RequestParam("carId") Long carId) {
        orderService.handleCarInProd(orderId, carId);
        return "OK";
    }

    @PutMapping("/status/car_prod_completed")
    public String handleCarProdCompleted(@RequestParam("carId") Long carId) {
        orderService.handleCarProdCompleted(carId);
        return "OK";
    }

    @PutMapping("/status/car_delivered")
    public String handleCarDelivered(@RequestParam("carId") Long carId) {
        orderService.handleCarDelivered(carId);
        return "OK";
    }

    @PutMapping("/status/finish")
    public String handleCarFinished(@RequestParam("carId") Long carId) {
        orderService.handleCarFinished(carId);
        return "OK";
    }
}

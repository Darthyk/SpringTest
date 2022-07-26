package com.darthyk.springtest.service;

import com.darthyk.springtest.model.Order;

public interface OrderService {
    Order createOrder(Long userId);
    Order handleCarInProd(Long orderId, Long carId);

    Order handleCarProdCompleted(Long carId);

    Order handleCarDelivered(Long carId);

    Order handleCarFinished(Long carId);
}

package com.darthyk.springtest.repository;

import com.darthyk.springtest.model.Car;
import com.darthyk.springtest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderById(Long id);
    Order findOrderByCar(Car car);
}

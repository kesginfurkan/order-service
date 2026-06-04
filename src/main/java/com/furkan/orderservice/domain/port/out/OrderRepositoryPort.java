package com.furkan.orderservice.domain.port.out;

import com.furkan.orderservice.domain.model.Order;

public interface OrderRepositoryPort {

    Order save(Order order);
}

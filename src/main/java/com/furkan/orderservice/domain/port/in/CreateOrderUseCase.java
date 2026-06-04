package com.furkan.orderservice.domain.port.in;

import com.furkan.orderservice.domain.model.Order;

import java.math.BigDecimal;

public interface CreateOrderUseCase {

    Order createOrder(
            String productName,
            Integer quantity,
            BigDecimal price,
            String username
    );
}

package com.furkan.orderservice.domain.port.in;

import com.furkan.orderservice.domain.command.CreateOrderCommand;
import com.furkan.orderservice.domain.model.Order;

import java.math.BigDecimal;

public interface CreateOrderUseCase {

    Order createOrder(CreateOrderCommand command);
}

package com.furkan.orderservice.domain.command;

import java.math.BigDecimal;

public record CreateOrderCommand (
        String productName,
        Integer quantity,
        BigDecimal price,
        String username
) {

}

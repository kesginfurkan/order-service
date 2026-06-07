package com.furkan.orderservice.infrastructure.adapter.in.web;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        String productName,
        Integer quantity,
        BigDecimal price,
        String createdBy,
        Instant createdAt
) {
}

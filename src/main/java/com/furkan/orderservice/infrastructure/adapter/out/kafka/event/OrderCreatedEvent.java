package com.furkan.orderservice.infrastructure.adapter.out.kafka.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent (
        UUID orderId,
        String productName,
        Integer quantity,
        BigDecimal price,
        String createdBy,
        Instant createdAt
) {}

package com.furkan.orderservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Order {
    private UUID id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private String createdBy;
    private Instant createdAt;
}

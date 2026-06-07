package com.furkan.orderservice.infrastructure.adapter.out.persistence;

import com.furkan.orderservice.domain.model.Order;

public final class OrderMapper {
    private OrderMapper() {}

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .createdBy(order.getCreatedBy())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static Order toDomain(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

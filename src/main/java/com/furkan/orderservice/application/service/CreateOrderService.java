package com.furkan.orderservice.application.service;

import com.furkan.orderservice.domain.command.CreateOrderCommand;
import com.furkan.orderservice.domain.model.Order;
import com.furkan.orderservice.domain.port.in.CreateOrderUseCase;
import com.furkan.orderservice.domain.port.out.NotificationPublisherPort;
import com.furkan.orderservice.domain.port.out.OrderRepositoryPort;
import com.furkan.orderservice.domain.port.out.SessionCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final NotificationPublisherPort notificationPublisherPort;
    private final SessionCachePort sessionCachePort;

    @Override
    public Order createOrder(
            CreateOrderCommand command
    ) {

        sessionCachePort.cacheAuthenticatedUser(command.username());

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .productName(command.productName())
                .quantity(command.quantity())
                .price(command.price())
                .createdBy(command.username())
                .createdAt(Instant.now())
                .build();

        Order savedOrder = orderRepositoryPort.save(order);

        notificationPublisherPort.publishOrderCreated(savedOrder);

        return savedOrder;
    }
}
